package com.cloud.ftl.ftltest.test.service.inft;

import com.cloud.ftl.ftlbasic.service.IBaseCache;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

import java.io.InputStream;

/**
 * IDailyAmountService service接口类
 * @author lijun
 */
public interface IDailyAmountService extends IBaseCache<DailyAmount>{

    void saveExcel(InputStream inputStream);
}
