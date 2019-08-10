package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

/**
  * 接口类 IDailyAmountDao
  * @author lijun
  */
@Repository
public interface IDailyAmountDao extends IBaseMapper<DailyAmount>{

    /**
     * 新增对象
     * @param entity
     * @return
     */
    Integer addDailyAmount(@Param("at") DailyAmount entity);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAddDailyAmount(List<DailyAmount> list);

    /**
     * 删除对象
     * @param daId
     * @return
     */
    Integer deleteDailyAmount(Long daId);

    /**
     * 批量删除对象
     * @param list
     */
    void batchDeleteDailyAmount(List<Long> list);

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}