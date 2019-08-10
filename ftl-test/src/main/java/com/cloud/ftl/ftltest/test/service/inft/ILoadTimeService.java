package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.service.IBaseService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
 * ILoadTimeService service接口类
 * @author lijun
 */
public interface ILoadTimeService extends IBaseService<LoadTime>{

    /**
     * 新增对象
     * @param loadTime
     * @return
     * @throws Exception
     */
    Integer addLoadTime(LoadTime loadTime) throws Exception;

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    void batchAddLoadTime(List<LoadTime> list) throws Exception;

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    void batchUpdateLoadTime(List<LoadTime> list,Boolean fullUpdate) throws Exception;

    /**
     * 删除对象
     * @param ltId
     * @return
     * @throws Exception
     */
    Integer deleteLoadTime(Long ltId) throws Exception;

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    void batchDeleteLoadTime(List<Long> list) throws Exception;

    /**
     * 保存记录
     * @param loadTime
     * @throws Exception
     */
     void saveLoadTime(LoadTime loadTime) throws Exception;

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
     void saveLoadTimeList(List<LoadTime> list) throws Exception;

}
