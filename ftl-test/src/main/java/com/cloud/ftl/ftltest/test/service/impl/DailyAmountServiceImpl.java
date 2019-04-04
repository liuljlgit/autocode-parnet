package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl implements IDailyAmountService {

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     * @throws Exception
     */
    @Override
    public DailyAmount loadDailyAmountByKey(Long daId) throws Exception {
        return null;
    }
}