package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftlbasic.service.AbstractBaseService;
import org.springframework.data.redis.core.RedisTemplate;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl extends AbstractBaseService<LoadTime> implements ILoadTimeService {

    public LoadTimeServiceImpl(ILoadTimeDao loadTimeDao,RedisTemplate<String,String> stringRedisTemplate){
        super(loadTimeDao,stringRedisTemplate);
    }

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    private ILoadTimeDao loadTimeDao;

    /**
     * 新增对象
     * @param loadTime
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addLoadTime(LoadTime loadTime) throws Exception {
        if(Objects.isNull(loadTime)){
            return 0;
        }
        if(Objects.isNull(loadTime.getLtId())){
            loadTime.setLtId(selectMaxId());
        }
        return loadTimeDao.addLoadTime(loadTime);
    }

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddLoadTime(List<LoadTime> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (LoadTime loadTime : list) {
            if(Objects.isNull(loadTime.getLtId())){
                loadTime.setLtId(selectMaxId());
            }
        }
        loadTimeDao.batchAddLoadTime(list);
    }

    /**
     * 删除对象
     * @param ltId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteLoadTime(Long ltId) throws Exception {
        if(Objects.isNull(ltId)){
            throw new BusiException("主键不能为空");
        }
        return loadTimeDao.deleteLoadTime(ltId);
    }

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteLoadTime(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        loadTimeDao.batchDeleteLoadTime(list);
    }

    /**
     * 保存记录
     * @param loadTime
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLoadTime(LoadTime loadTime) throws Exception {
        if(Objects.isNull(loadTime)){
           return ;
        }
        if(Objects.isNull(loadTime.getLtId())){
            loadTime.setLtId(selectMaxId());
            addLoadTime(loadTime);
        }else{
            updateLoadTime(loadTime,false);
        }
    }

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLoadTimeList(List<LoadTime> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        List<LoadTime> addList = list.stream().filter(e -> Objects.isNull(e.getLtId())).collect(Collectors.toList());
        List<LoadTime> updateList = list.stream().filter(e -> Objects.nonNull(e.getLtId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(addList)){
            addList = addList.stream().map(e->{
                e.setLtId(selectMaxId());
                return e;
            }).collect(Collectors.toList());
            batchAddLoadTime(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdateLoadTime(updateList,false);
        }
    }

}