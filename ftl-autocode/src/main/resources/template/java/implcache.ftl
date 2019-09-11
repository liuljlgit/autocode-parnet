package ${implCachePackagePath};

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftlbasic.utils.QueryKeyUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import ${inftCachePackagePath}.I${className}Cache;
import ${entityPackagePath}.${className};
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * I${className}Cache cache实现类
 * @author lijun
 */
@Slf4j
public class ${className}CacheImpl extends BaseServiceImpl<${className}> implements I${className}Cache {

    private final static String CLS_NAME = ${className}.class.getSimpleName();
    private final static String IDS_KEY = CLS_NAME.concat(":IDS:");
    private final static String QUERY_KEY = CLS_NAME.concat(":QUERY:");

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public ${className} cacheSelectById(Serializable id, String... nullErrMsg) {
        String entityKey = CLS_NAME.concat(":").concat(String.valueOf(id));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ${className} cacheVal = (${className})redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(cacheVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return cacheVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        ${className} dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
            redisTemplate.expire(entityKey,30, TimeUnit.MINUTES);
        }
        return dbVal;
    }

    @Override
    public ${className} cacheSelectOne(${className} query, String... nullErrMsg) {
        query.setPage(1);
        query.setPageSize(1);
        String queryKey = QUERY_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.TRUE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ${className} cacheVal = (${className})redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return cacheVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        ${className} dbVal = super.selectOne(query, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(queryKey,dbVal);
            redisTemplate.expire(queryKey,30, TimeUnit.MINUTES);
        }
        return dbVal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<${className}> cacheSelectList(${className} query, String... emptyErrMsg) {
        String queryKey = IDS_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        List<String> cacheIds = (List<String>)redisTemplate.opsForValue().get(queryKey);
        if(!CollectionUtils.isEmpty(cacheIds)){
            List<Object> cacheValues = redisTemplate.opsForValue().multiGet(cacheIds);
            if(Objects.nonNull(cacheValues) && cacheIds.size() == cacheValues.size()){
                log.info(" --------- Get Entity From Cache --------- ");
                return JSONArray.parseArray(JSON.toJSONString(cacheValues), ${className}.class);
            }
        }
        log.info(" --------- Get Entity From DB --------- ");
        List<${className}> dbValues = super.selectList(query, emptyErrMsg);
        List<String> dbIds = Lists.newArrayList();
        dbValues.forEach(e -> {
            String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.get${IdColEntity.fieldJavaName?cap_first}()));
            dbIds.add(entityKey);
            redisTemplate.opsForValue().set(entityKey,e);
            redisTemplate.expire(entityKey,30,TimeUnit.MINUTES);
        });
        redisTemplate.opsForValue().set(queryKey,dbIds);
        redisTemplate.expire(queryKey,30,TimeUnit.MINUTES);
        return dbValues;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<${className}> cacheSelectList(${className} query, List<String> fieldList, String... emptyErrMsg) {
        if(CollectionUtils.isEmpty(fieldList)){
            throw new BusiException("请指定查询的域");
        }
        String queryKey = QUERY_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE,fieldList.toArray(new String[fieldList.size()])));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Object cacheValues = redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheValues)){
            log.info(" --------- Get Entity From Cache --------- ");
            return JSONArray.parseArray(JSON.toJSONString(cacheValues),${className}.class);
        }
        log.info(" --------- Get Entity From DB --------- ");
        List<${className}> dbValues = super.selectList(query, fieldList, emptyErrMsg);
        if(!CollectionUtils.isEmpty(dbValues)){
            redisTemplate.opsForValue().set(queryKey,dbValues);
            redisTemplate.expire(queryKey,30, TimeUnit.MINUTES);
        }
        return dbValues;
    }

    @Override
    public List<${className}> cacheSelectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) {
        List<String> queryIds = list.stream().map(e -> CLS_NAME.concat(":").concat(String.valueOf(e))).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(queryIds)){
            List<Object> cacheValues = redisTemplate.opsForValue().multiGet(queryIds);
            if(Objects.nonNull(cacheValues)){
                log.info(" --------- Get Entity From Cache --------- ");
                List<${className}> cacheEntitys = JSONArray.parseArray(JSON.toJSONString(cacheValues), ${className}.class);
                if(queryIds.size() == cacheEntitys.size()){
                    return cacheEntitys;
                } else {
                    List<String> cacheIds = cacheEntitys.stream()
                        .map(e -> CLS_NAME.concat(":").concat(String.valueOf(e.get${IdColEntity.fieldJavaName?cap_first}())))
                        .collect(Collectors.toList());
                    queryIds.removeAll(cacheIds);
                    List<${className}> dbValues = super.selectBatchIds(queryIds, emptyErrMsg);
                    if(!CollectionUtils.isEmpty(dbValues)){
                        cacheEntitys.addAll(dbValues);
                    }
                    return cacheEntitys;
                }
            } else {
                log.info(" --------- Get Entity From DB --------- ");
                List<${className}> dbValues = super.selectBatchIds(list, emptyErrMsg);
                dbValues.forEach(e -> {
                    String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.get${IdColEntity.fieldJavaName?cap_first}()));
                    redisTemplate.opsForValue().set(entityKey,e);
                    redisTemplate.expire(entityKey,30,TimeUnit.MINUTES);
                });
                return dbValues;
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public PageBean<${className}> cacheSelectPage(${className} query) {
        return super.selectPage(query);
    }

    @Override
    public PageBean<${className}> cacheSelectPage(${className} query, List<String> fieldList) {
        return super.selectPage(query, fieldList);
    }

    @Override
    public Long cacheSelectCount(${className} query) {
        return super.selectCount(query);
    }

    @Override
    public int update(${className} entity, Update... args) {
        return super.update(entity, args);
    }

    @Override
    public int updateByMap(${className} oEntity, FuncMap funcMap) {
        return super.updateByMap(oEntity, funcMap);
    }

    @Override
    public void updateBatch(List<${className}> list, Update... args) {
        super.updateBatch(list, args);
    }

    @Override
    public int add(${className} entity) {
        return super.add(entity);
    }

    @Override
    public void addBatch(List<${className}> list) {
        super.addBatch(list);
    }

    @Override
    public void addBatch(List<${className}> list, int batchSize) {
        super.addBatch(list, batchSize);
    }

    @Override
    public void delete(${className} entity) {
        super.delete(entity);
    }

    @Override
    public int deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    public void deleteBatchIds(Collection<? extends Serializable> list) {
        super.deleteBatchIds(list);
    }

    @Override
    public void save(${className} ${objectName}, Update... args) {
        super.save(${objectName}, args);
    }

    @Override
    public void saveBatch(List<${className}> list, Update... args) {
        super.saveBatch(list, args);
    }

}