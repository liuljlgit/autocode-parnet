package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.util.CollectionUtils;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;
import com.cloud.ftl.ftltest.test.cache.inft.ILoadTimeRedis;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl implements ILoadTimeService {

    private static final Logger logger = LoggerFactory.getLogger(LoadTimeServiceImpl.class);

    @Autowired
    private ILoadTimeDao loadTimeDao;
    @Autowired
    private ILoadTimeRedis loadTimeRedis;

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     * @throws Exception
     */
    @Override
    public LoadTime loadLoadTimeByKey(Long ltId) throws Exception {
        if(Objects.isNull(ltId)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        LoadTime loadTime = loadTimeDao.loadLoadTimeByKey(ltId);
        if(Objects.isNull(loadTime)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return loadTime;
    }

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public LoadTime selectOneLoadTime(LoadTime query) throws Exception {
        List<LoadTime> list = findLoadTimeList(query);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<LoadTime> getLoadTimePageList(LoadTime query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = loadTimeDao.getTotalLoadTime(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<LoadTime> loadTimeList = findLoadTimeList(query);
        return new PageBean<>(totalPage,total,loadTimeList);
    }


    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<LoadTime> findLoadTimeList(LoadTime query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return loadTimeDao.findLoadTimeList(query);
    }

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
            loadTime.setLtId(loadTimeRedis.getLoadTimeId());
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
                loadTime.setLtId(loadTimeRedis.getLoadTimeId());
            }
        }
        loadTimeDao.batchAddLoadTime(list);
    }

    /**
     * 更新对象
     * @param loadTime
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateLoadTime(LoadTime loadTime,Boolean fullUpdate) throws Exception {
        if(Objects.isNull(loadTime)){
            return 0;
        }
        if(Objects.isNull(loadTime.getLtId())){
            throw new BusiException("主键不能为空");
        }
        Integer result;
        if(fullUpdate){
            result = loadTimeDao.fullUpdateLoadTime(loadTime);
        } else {
            result = loadTimeDao.updateLoadTime(loadTime);
        }
        return result;
    }

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateLoadTime(List<LoadTime> list,Boolean fullUpdate) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (LoadTime loadTime : list) {
            if(Objects.isNull(loadTime.getLtId())){
                throw new BusiException("主键不能为空");
            }
        }
        if(fullUpdate){
            loadTimeDao.batchFullUpdateLoadTime(list);
        } else {
            loadTimeDao.batchUpdateLoadTime(list);
        }
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
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public List<LoadTime> findLoadTimeByIdList(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        return loadTimeDao.findLoadTimeByIdList(list);
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
            loadTime.setLtId(loadTimeRedis.getLoadTimeId());
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
                e.setLtId(loadTimeRedis.getLoadTimeId());
                return e;
            }).collect(Collectors.toList());
            batchAddLoadTime(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdateLoadTime(updateList,false);
        }
    }

}