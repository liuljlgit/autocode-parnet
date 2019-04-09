package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
  * 接口类 ILoadTimeDao
  * @author lijun
  */
@Repository
public interface ILoadTimeDao {

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     */
    LoadTime loadLoadTimeByKey(Long ltId);
}