package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;

/**
  * 接口类 IDailyAmountDao
  * @author lijun
  */
@Repository
public interface IDailyAmountDao {

    /**
     * 获取表的最大ID
     * @return
     */
     Long selectMaxDailyAmountId();

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     */
    DailyAmount loadDailyAmountByKey(Long daId);

    /**
     * 查询列表
     * @param query
     * @return
     */
    List<DailyAmount> findDailyAmountList(DailyAmountQuery query);

    /**
     * 获取查询总数
     * @param query
     * @return
     */
    Long getTotalDailyAmount(DailyAmountQuery query);
}