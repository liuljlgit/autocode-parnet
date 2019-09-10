package com.cloud.ftl.ftltest.test.cache.impl;

import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.cache.inft.IDailyAmountCache;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * IDailyAmountCache cache实现类
 * @author lijun
 */
@Service("dailyAmountCache")
public class DailyAmountCacheImpl extends BaseServiceImpl<DailyAmount> implements IDailyAmountCache {

    @Override
    public Long selectMaxId() {
        return super.selectMaxId();
    }

    @Override
    public DailyAmount selectById(Serializable id, String... nullErrMsg) {
        return super.selectById(id, nullErrMsg);
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