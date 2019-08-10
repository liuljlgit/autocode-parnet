package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
  * 接口类 ILoadTimeDao
  * @author lijun
  */
@Repository
public interface ILoadTimeDao extends IBaseMapper<LoadTime>{

    /**
     * 新增对象
     * @param entity
     * @return
     */
    Integer addLoadTime(@Param("at") LoadTime entity);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAddLoadTime(List<LoadTime> list);

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