package com.cloud.ftl.ftltest.test.cache.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftlbasic.utils.QueryKeyUtil;
import com.cloud.ftl.ftltest.test.cache.inft.IDailyAmountCache;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * IDailyAmountCache cache实现类
 * @author lijun
 */
@Slf4j
@Service("dailyAmountCache")
public class DailyAmountCacheImpl extends BaseServiceImpl<DailyAmount> implements IDailyAmountCache {

    private final static String CLS_NAME = DailyAmount.class.getSimpleName();
    private final static String PAGE_IDS_KEY = CLS_NAME.concat(":PAGE:").concat("IDS:");
    private final static String PAGE_TOTAL_KEY = CLS_NAME.concat(":PAGE:").concat("TOTAL:");
    private final static String CUSTOM_QUERY_KEY = CLS_NAME.concat(":CUSTOM_QUERY:");

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
        DailyAmount cacheVal = (DailyAmount)redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(cacheVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return cacheVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        DailyAmount dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
            redisTemplate.expire(entityKey,30, TimeUnit.MINUTES);
        }
        return dbVal;
    }

    @Override
    public DailyAmount selectOne(DailyAmount query, String... nullErrMsg) {
        query.setPage(1);
        query.setPageSize(1);
        String queryKey = CUSTOM_QUERY_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.TRUE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        DailyAmount cacheVal = (DailyAmount)redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return cacheVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        DailyAmount dbVal = super.selectOne(query, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(queryKey,dbVal);
            redisTemplate.expire(queryKey,30, TimeUnit.MINUTES);
        }
        return dbVal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DailyAmount> selectList(DailyAmount query, String... emptyErrMsg) {
        String queryKey = PAGE_IDS_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        List<String> cacheIds = (List<String>)redisTemplate.opsForValue().get(queryKey);
        if(!CollectionUtils.isEmpty(cacheIds)){
            List<Object> cacheValues = redisTemplate.opsForValue().multiGet(cacheIds);
            if(Objects.nonNull(cacheValues) && cacheIds.size() == cacheValues.size()){
                log.info(" --------- Get Entity From Cache --------- ");
                return JSONArray.parseArray(JSON.toJSONString(cacheValues), DailyAmount.class);
            }
        }
        log.info(" --------- Get Entity From DB --------- ");
        List<DailyAmount> dbValues = super.selectList(query, emptyErrMsg);
        List<String> dbIds = Lists.newArrayList();
        dbValues.forEach(e -> {
            String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.getDaId()));
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
    public List<DailyAmount> selectList(DailyAmount query, List<String> fieldList, String... emptyErrMsg) {
        if(CollectionUtils.isEmpty(fieldList)){
            throw new BusiException("请指定查询的域");
        }
        String queryKey = CUSTOM_QUERY_KEY.concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE,fieldList.toArray(new String[fieldList.size()])));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Object cacheValues = redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheValues)){
            log.info(" --------- Get Entity From Cache --------- ");
            return JSONArray.parseArray(JSON.toJSONString(cacheValues),DailyAmount.class);
        }
        log.info(" --------- Get Entity From DB --------- ");
        List<DailyAmount> dbValues = super.selectList(query, fieldList, emptyErrMsg);
        if(!CollectionUtils.isEmpty(dbValues)){
            redisTemplate.opsForValue().set(queryKey,dbValues);
            redisTemplate.expire(queryKey,30, TimeUnit.MINUTES);
        }
        return dbValues;
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