package com.cloud.ftl.ftltest.test.dao;

import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.query.LoadTimeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
  * 接口类 ILoadTimeDao
  * @author lijun
  */
@Repository
public interface ILoadTimeDao {

    /**
     * 获取表的最大ID
     * @return
     */
     Long selectMaxLoadTimeId();

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     */
    LoadTime loadLoadTimeByKey(Long ltId);

    /**
     * 查询列表
     * @param query
     * @return
     */
    List<LoadTime> findLoadTimeList(LoadTimeQuery query);

    /**
     * 获取查询总数
     * @param query
     * @return
     */
    Long getTotalLoadTime(LoadTimeQuery query);

    /**
     * 新增对象
     * @param loadTime
     * @return
     */
    Integer addLoadTime(LoadTime loadTime);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAddLoadTime(List<LoadTime> list);

    /**
     * 更新对象
     * @param loadTime
     * @return
     */
    Integer updateLoadTime(LoadTime loadTime);

    /**
     * 批量更新对象
     * @param list
     */
    void batchUpdateLoadTime(List<LoadTime> list);

    /**
     * 更新对象（全更新）
     * @param loadTime
     * @return
     */
    Integer fullUpdateLoadTime(LoadTime loadTime);

    /**
     * 批量更新对象（全更新）
     * @param list
     */
    void batchFullUpdateLoadTime(List<LoadTime> list);

    /**
     * 删除对象
     * @param ltId
     * @return
     */
    Integer deleteLoadTime(Long ltId);

    /**
     * 批量删除对象
     * @param list
     */
    void batchDeleteLoadTime(List<Long> list);

   /**
    * 根据ID列表从数据库中查询列表
    * @param list
    * @return
    */
    List<LoadTime> findLoadTimeByIdList(List<Long> list);

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}