package com.cloud.ftl.ftltest.test.cache.impl;

import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.cache.inft.ILoadTimeCache;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * ILoadTimeCache cache实现类
 * @author lijun
 */
@Service("loadTimeCache")
public class LoadTimeCacheImpl extends BaseServiceImpl<LoadTime> implements ILoadTimeCache {

    private final static String CLS_NAME = LoadTime.class.getSimpleName();
    private final static String PAGE_IDS_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("IDS");
    private final static String PAGE_TOTAL_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("TOTAL");

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Long selectMaxId() {
        return super.selectMaxId();
    }

    @Override
    public LoadTime selectById(Serializable id, String... nullErrMsg) {
        String entityKey = CLS_NAME.concat(":").concat(String.valueOf(id));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        LoadTime redisVal = (LoadTime)redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(redisVal)){
            return redisVal;
        }
        LoadTime dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
        }
        return dbVal;
    }

    @Override
    public LoadTime selectOne(LoadTime query, String... nullErrMsg) {
        return super.selectOne(query, nullErrMsg);
    }

    @Override
    public List<LoadTime> selectList(LoadTime query, String... emptyErrMsg) {
        return super.selectList(query, emptyErrMsg);
    }

    @Override
    public List<LoadTime> selectList(LoadTime query, List<String> fieldList, String... emptyErrMsg) {
        return super.selectList(query, fieldList, emptyErrMsg);
    }

    @Override
    public List<LoadTime> selectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) {
        return super.selectBatchIds(list, emptyErrMsg);
    }

    @Override
    public Long selectCount(LoadTime query) {
        return super.selectCount(query);
    }

    @Override
    public int update(LoadTime entity, Update... args) {
        return super.update(entity, args);
    }

    @Override
    public int updateByMap(LoadTime oEntity, FuncMap funcMap) {
        return super.updateByMap(oEntity, funcMap);
    }

    @Override
    public void updateBatch(List<LoadTime> list, Update... args) {
        super.updateBatch(list, args);
    }

    @Override
    public int add(LoadTime entity) {
        return super.add(entity);
    }

    @Override
    public void addBatch(List<LoadTime> list) {
        super.addBatch(list);
    }

    @Override
    public void addBatch(List<LoadTime> list, int batchSize) {
        super.addBatch(list, batchSize);
    }

    @Override
    public void delete(LoadTime entity) {
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
    public void save(LoadTime loadTime, Update... args) {
        super.save(loadTime, args);
    }

    @Override
    public void saveBatch(List<LoadTime> list, Update... args) {
        super.saveBatch(list, args);
    }

}