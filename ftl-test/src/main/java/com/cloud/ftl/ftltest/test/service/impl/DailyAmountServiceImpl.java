package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl extends BaseServiceImpl<DailyAmount> implements IDailyAmountService {


}