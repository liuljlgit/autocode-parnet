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
}