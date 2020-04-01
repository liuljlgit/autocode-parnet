package com.cloud.ftl.ftlbasic.service;


import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import com.cloud.ftl.ftlbasic.utils.FieldCacheUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Slf4j
public class BaseServiceImpl<T> implements IBaseService<T> {

    private final static Long START_ID = 1000000L;

    @Autowired
    private IBaseMapper<T> baseMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public BaseServiceImpl() {}

    @Override
    public Long selectMaxId() {
        ParameterizedType type = (ParameterizedType)this.getClass().getSuperclass().getGenericSuperclass();
        Class tClass = (Class) type.getActualTypeArguments()[0];
        redisTemplate.setEnableTransactionSupport(false);
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            String tableIdKey = "SEQ:".concat(tClass.getSimpleName());
            if (!Objects.requireNonNull(connection.exists(tableIdKey.getBytes()))){
                Long id = baseMapper.selectMaxId();
                id = ( null == id || id == 0) ?  START_ID +  Long.parseLong("1") : ++ id;
                if (Objects.requireNonNull(connection.setNX(tableIdKey.getBytes(), String.valueOf(id).getBytes()))){
                    return  id;
                }
            }
            return connection.incr(Objects.requireNonNull(redisTemplate.getStringSerializer().serialize(tableIdKey)));
        });
    }

    @Override
    public T selectById(Serializable id,String... nullErrMsg) {
        if(Objects.isNull(id)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        T t = baseMapper.selectById(id);
        if(Objects.isNull(t) && nullErrMsg.length > 0){
            throw new BusiException(nullErrMsg[0]);
        }
        return t;
    }

    @Override
    public T selectOne(T query,String... nullErrMsg) {
        try{
            FieldCacheUtil.setPMet.invoke(query,1);
            FieldCacheUtil.setPSMet.invoke(query,1);
            List<T> list = selectList(query);
            if(!CollectionUtils.isEmpty(list)){
                return list.get(0);
            }
            if(nullErrMsg.length > 0){
                throw new BusiException(nullErrMsg[0]);
            } else {
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    public List<T> selectList(T query,String... emptyErrMsg)  {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        List<T> ts = baseMapper.selectList(query);
        if(CollectionUtils.isEmpty(ts) && emptyErrMsg.length > 0){
            throw new BusiException(emptyErrMsg[0]);
        }
        return ts;
    }

    @Override
    public List<T> selectList(T query, List<String> fieldList, String... emptyErrMsg)  {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        if(CollectionUtils.isEmpty(fieldList)){
            throw new BusiException("请指定查询的域");
        }
        List<T> ts = baseMapper.selectFieldList(query,fieldList);
        if(CollectionUtils.isEmpty(ts) && emptyErrMsg.length > 0){
            throw new BusiException(emptyErrMsg[0]);
        }
        return ts;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> list,String... emptyErrMsg) {
        List<T> ts = baseMapper.selectBatchIds(list);
        if(CollectionUtils.isEmpty(ts) && emptyErrMsg.length > 0){
            throw new BusiException(emptyErrMsg[0]);
        }
        return ts;
    }

    @Override
    public PageBean<T> selectPage(T query) {
        try {
            Integer page = (Integer)FieldCacheUtil.getPMet.invoke(query);
            Integer pageSize = (Integer)FieldCacheUtil.getPSMet.invoke(query);
            if(Objects.isNull(page) || Objects.isNull(pageSize)){
                throw new BusiException("请设置正确的分页值和分页大小");
            }
            FieldCacheUtil.setIndexMet.invoke(query,(page-1)*pageSize);
            Long total = selectCount(query);
            Long totalPage = (long)Math.ceil((double)total / pageSize);
            return new PageBean<>(totalPage,total,selectList(query));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    public PageBean<T> selectPage(T query, List<String> fieldList) {
        try {
            if(CollectionUtils.isEmpty(fieldList)){
                throw new BusiException("请指定查询的域");
            }
            Integer page = (Integer)FieldCacheUtil.getPMet.invoke(query);
            Integer pageSize = (Integer)FieldCacheUtil.getPSMet.invoke(query);
            if(Objects.isNull(page) || Objects.isNull(pageSize)){
                throw new BusiException("请设置正确的分页值和分页大小");
            }
            FieldCacheUtil.setIndexMet.invoke(query,(page-1)*pageSize);
            Long total = selectCount(query);
            Long totalPage = (long)Math.ceil((double)total / pageSize);
            return new PageBean<>(totalPage,total,selectList(query,fieldList));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    public Long selectCount(T query) {
        return  baseMapper.selectCount(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity,Update... args) {
        if(Objects.isNull(entity)){
            return 0;
        }
        if(args.length > 1){
            throw new BusiException("更新失败，Update入参异常");
        }
        Field priField = getPriKeyField(entity);
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("更新失败，当前操作表没有主键");
        }
        try {
            Object priKey = priField.get(entity);
            if(Objects.isNull(priKey)){
                throw new BusiException("更新失败，主键不能为空");
            }
            //更新不为空的操作
            if(args.length == 0 || args[0].equals(Update.NOT_NULL)){
                return baseMapper.updateNotNull(entity);
            }
            if(args[0].equals(Update.WITH_NULL)){
                return baseMapper.updateWithNull(entity);
            }
            return 0;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByMap(T oEntity, Map<String, Object> updateMap) {
        if(Objects.isNull(oEntity)){
            return 0;
        }
        if(Objects.isNull(updateMap) || updateMap.size() == 0){
            return 0;
        }
        return baseMapper.updateByMap(updateMap,oEntity);
    }

    private <T> Field getPriKeyField(T entity) {
        Class<?> tClass = entity.getClass();
        Field priField = FieldCacheUtil.priKeyCache.getOrDefault(tClass, null);
        if(Objects.nonNull(priField)){
            return priField;
        }
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
            if(Objects.nonNull(annotation)){
                field.setAccessible(true);
                FieldCacheUtil.priKeyCache.put(tClass,field);
                return field;
            }
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBatch(List<T> list,Update... args) {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        if(args.length > 1){
            throw new BusiException("批量更新失败，Update入参异常");
        }
        Field priField = getPriKeyField(list.get(0));
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("批量更新失败，当前操作表没有主键");
        }
        try {
            //检查主键是否为空
            for (T t : list) {
                Object key = priField.get(t);
                if(Objects.isNull(key)){
                    throw new BusiException("批量更新失败，主键不能为空");
                }
            }
            //更新不为空的操作
            if(args.length == 0 || args[0].equals(Update.NOT_NULL)){
                baseMapper.updateBatchNotNull(list);
            }
            if(args[0].equals(Update.WITH_NULL)){
                baseMapper.updateBatchWithNull(list);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(T entity) {
        if(Objects.isNull(entity)){
            return 0;
        }
        Field priField = getPriKeyField(entity);
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("新增失败，当前操作表没有主键");
        }
        try {
            Object priKey = priField.get(entity);
            if(Objects.isNull(priKey)){
                priField.set(entity,selectMaxId());
            }
            return baseMapper.add(entity);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBatch(List<T> list) {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        Field priField = getPriKeyField(list.get(0));
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("批量新增失败，当前操作表没有主键");
        }
        try{
            for (T t : list) {
                Object key = priField.get(t);
                if(Objects.isNull(key)){
                    priField.set(t,selectMaxId());
                }
            }
            baseMapper.addBatch(list);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBatch(List<T> list, int batchSize) {
        if (CollectionUtils.isEmpty(list)){
            return;
        }
        if(batchSize != 0) {
            int size = list.size();
            int times = (size % batchSize == 0) ? (size / batchSize) : (size / batchSize + 1);
            for(int i = 0; i < times; i++) {
                int start = i * batchSize;
                int end = (start + batchSize) <= size ? (start + batchSize) : size;
                List<T> subList = list.subList(start, end);
                this.addBatch(subList);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(T entity) {
        if(Objects.isNull(entity)){
            return;
        }
        baseMapper.delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        if(Objects.isNull(id)){
            return 0;
        }
        return baseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchIds(Collection<? extends Serializable> list) {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        baseMapper.deleteBatchIds(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T t, Update... args){
        if(Objects.isNull(t)){
            return ;
        }
        Field priField = getPriKeyField(t);
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("保存失败，当前操作表没有主键");
        }
        try {
            Object priKey = priField.get(t);
            if(Objects.isNull(priKey)){
                add(t);
            }else{
                update(t,args);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatch(List<T> list,Update... args){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        Field priField = getPriKeyField(list.get(0));
        //没有找到主键
        if(Objects.isNull(priField)){
            throw new BusiException("保存失败，当前操作表没有主键");
        }
        try{
            List<T> addList = new ArrayList<>();
            List<T> updateList = new ArrayList<>();
            for (T t : list) {
                Object key = priField.get(t);
                if(Objects.isNull(key)){
                    addList.add(t);
                }else{
                    updateList.add(t);
                }
            }
            if(!CollectionUtils.isEmpty(addList)){
                addBatch(addList);
            }
            if(!CollectionUtils.isEmpty(updateList)){
                updateBatch(updateList,args);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }


}
