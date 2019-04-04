package com.cloud.ftl.ftltest.test.service.inft;

import com.cloud.ftl.ftltest.test.entity.DailyAmount;

/**
 * IDailyAmountService service接口类
 * @author lijun
 */
public interface IDailyAmountService {

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     * @throws Exception
     */
   DailyAmount loadDailyAmountByKey(Long daId) throws Exception;
}
