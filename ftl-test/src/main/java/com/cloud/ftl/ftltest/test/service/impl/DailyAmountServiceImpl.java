package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.cache.impl.DailyAmountCacheImpl;
import com.cloud.ftl.ftltest.test.service.IDailyAmountService;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl extends DailyAmountCacheImpl implements IDailyAmountService {


}