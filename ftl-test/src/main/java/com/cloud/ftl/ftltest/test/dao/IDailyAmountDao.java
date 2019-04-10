package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

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
}