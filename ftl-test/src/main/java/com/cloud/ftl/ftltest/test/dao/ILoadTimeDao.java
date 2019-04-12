package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.query.LoadTimeQuery;

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
}