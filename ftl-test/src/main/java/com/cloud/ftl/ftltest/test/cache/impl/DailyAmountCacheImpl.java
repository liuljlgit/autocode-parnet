package com.cloud.ftl.ftltest.test.cache.impl;

import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.cache.inft.IDailyAmountCache;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * IDailyAmountCache cache实现类
 * @author lijun
 */
@Service("dailyAmountCache")
@Slf4j
public class DailyAmountCacheImpl extends BaseServiceImpl<DailyAmount> implements IDailyAmountCache {

    private final static String CLS_NAME = DailyAmount.class.getSimpleName();
    private final static String PAGE_IDS_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("IDS");
    private final static String PAGE_TOTAL_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("TOTAL");
    private final static String CUSTOM_QUERY_KEY = "CUSTOM:QUERY:".concat(CLS_NAME);

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Long selectMaxId() {
        return super.selectMaxId();
    }

    @Override
    public DailyAmount selectById(Serializable id, String... nullErrMsg) {
        String entityKey = CLS_NAME.concat(":").concat(String.valueOf(id));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        DailyAmount redisVal = (DailyAmount)redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(redisVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return redisVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        DailyAmount dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
        }
        return dbVal;
    }

    @Override
    public DailyAmount selectOne(DailyAmount query, String... nullErrMsg) {
        return super.selectOne(query, nullErrMsg);
    }

    @Override
    public List<DailyAmount> selectList(DailyAmount query, String... emptyErrMsg) {
        return super.selectList(query, emptyErrMsg);
    }

    @Override
    public List<DailyAmount> selectList(DailyAmount query, List<String> fieldList, String... emptyErrMsg) {
        return super.selectList(query, fieldList, emptyErrMsg);
    }

    @Override
    public List<DailyAmount> selectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) {
        return super.selectBatchIds(list, emptyErrMsg);
    }

    @Override
    public Long selectCount(DailyAmount query) {
        return super.selectCount(query);
    }

    @Override
    public int update(DailyAmount entity, Update... args) {
        return super.update(entity, args);
    }

    @Override
    public int updateByMap(DailyAmount oEntity, FuncMap funcMap) {
        return super.updateByMap(oEntity, funcMap);
    }

    @Override
    public void updateBatch(List<DailyAmount> list, Update... args) {
        super.updateBatch(list, args);
    }

    @Override
    public int add(DailyAmount entity) {
        return super.add(entity);
    }

    @Override
    public void addBatch(List<DailyAmount> list) {
        super.addBatch(list);
    }

    @Override
    public void addBatch(List<DailyAmount> list, int batchSize) {
        super.addBatch(list, batchSize);
    }

    @Override
    public void delete(DailyAmount entity) {
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
    public void save(DailyAmount dailyAmount, Update... args) {
        super.save(dailyAmount, args);
    }

    @Override
    public void saveBatch(List<DailyAmount> list, Update... args) {
        super.saveBatch(list, args);
    }

}