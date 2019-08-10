package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
 * ILoadTimeService service接口类
 * @author lijun
 */
public interface ILoadTimeService {

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     * @throws Exception
     */
    LoadTime loadLoadTimeByKey(Long ltId) throws Exception;

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    LoadTime selectOneLoadTime(LoadTime query) throws Exception;

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<LoadTime> getLoadTimePageList(LoadTime query) throws Exception;

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<LoadTime> findLoadTimeList(LoadTime query) throws Exception;

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
     * 更新对象
     * @param loadTime
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    Integer updateLoadTime(LoadTime loadTime,Boolean fullUpdate) throws Exception;

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
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    List<LoadTime> findLoadTimeByIdList(List<Long> list) throws Exception;

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
