package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.annotation.UseCache;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
@UseCache
public class LoadTimeServiceImpl extends BaseServiceImpl<LoadTime> implements ILoadTimeService {

    @Override
    public LoadTime selectById(Serializable id, String... nullErrMsg) {
        return super.selectById(id, nullErrMsg);
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

    //---------------- 自定义方法请写在下面 ----------------------------------------------------------------

}