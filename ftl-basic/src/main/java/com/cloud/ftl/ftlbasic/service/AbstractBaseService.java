package com.cloud.ftl.ftlbasic.service;


import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractBaseService<T> implements IBaseService<T> {

    private final static Long START_ID = 1000000L;

    private IBaseMapper<T> baseMapper;

    private RedisTemplate<String, String> redisTemplate;

    public AbstractBaseService() {
    }

    public AbstractBaseService(IBaseMapper<T> baseMapper,RedisTemplate<String, String> redisTemplate) {
        this.baseMapper = baseMapper;
        this.redisTemplate = redisTemplate;
    }

    public Long selectMaxId() throws BusiException {
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class tClass = (Class) type.getActualTypeArguments()[0];
        redisTemplate.setEnableTransactionSupport(false);
        return redisTemplate.execute((RedisCallback<Long>) connection->{
            String tableIdKey = "SEQ:".concat(tClass.getSimpleName());
            if ( !connection.exists(tableIdKey.getBytes())){
                Long id = baseMapper.selectMaxId();
                id = ( null == id || id == 0) ?  START_ID +  Long.valueOf("1") : ++ id;
                if ( connection.setNX(tableIdKey.getBytes(), String.valueOf(id).getBytes())){
                    return  id;
                }
            }
            return connection.incr(redisTemplate.getStringSerializer().serialize(tableIdKey));
        });
    }

    public T selectById(Serializable id) throws BusiException {
        if(Objects.isNull(id)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        return baseMapper.selectById(id);
    }

    public T selectOne(T query) throws BusiException {
        List<T> list = selectList(query);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    public List<T> selectList(T query) throws BusiException {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return baseMapper.selectList(query);
    }

    public PageBean<T> selectPage(T query) throws BusiException {
        Class<?> aClass = query.getClass();
        try {
            Field pField = aClass.getDeclaredField("page");
            Field psField = aClass.getDeclaredField("pageSize");
            pField.setAccessible(true);
            psField.setAccessible(true);
            if(Objects.isNull(pField.get(query)) || Objects.isNull(psField.get(query))){
                throw new BusiException("page and pageSize can not be null");
            }
            Long total = selectCount(query);
            Long totalPage = (long)Math.ceil((double)total / (Integer)psField.get(query));
            return new PageBean<>(totalPage,total,selectList(query));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
    }

    public Long selectCount(T query) throws BusiException {
        return  baseMapper.selectCount(query);
    }

}
