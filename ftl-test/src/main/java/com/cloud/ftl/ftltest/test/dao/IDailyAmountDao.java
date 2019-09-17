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

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}