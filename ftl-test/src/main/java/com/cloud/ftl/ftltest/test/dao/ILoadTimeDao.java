package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
  * 接口类 ILoadTimeDao
  * @author lijun
  */
@Repository
public interface ILoadTimeDao extends IBaseMapper<LoadTime>{

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

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}