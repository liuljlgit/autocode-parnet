package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;
import com.cloud.ftl.ftltest.test.query.LoadTimeQuery;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl implements ILoadTimeService {

    private static final Logger logger = LoggerFactory.getLogger(LoadTimeServiceImpl.class);

    @Autowired
    private ILoadTimeDao loadTimeDao;

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
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<LoadTime> getLoadTimePageList(LoadTimeQuery query) throws Exception {
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
    public List<LoadTime> findLoadTimeList(LoadTimeQuery query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return loadTimeDao.findLoadTimeList(query);
    }
}