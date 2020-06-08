package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.ftl.ftltest.test.entity.BaseHoliday;

/**
  * 接口类 IBaseHolidayDao
  * @author lijun
  */
@Repository
public interface IBaseHolidayDao extends IBaseMapper<BaseHoliday>{

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}