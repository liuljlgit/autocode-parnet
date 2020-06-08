package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.ftl.ftltest.test.entity.ComUser;

/**
  * 接口类 IComUserDao
  * @author lijun
  */
@Repository
public interface IComUserDao extends IBaseMapper<ComUser>{

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}