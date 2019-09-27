package com.cloud.ftl.ftltest.test.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cloud.ftl.ftltest.test.excel.TestRead;
import com.cloud.ftl.ftltest.test.excel.TestReadListener;
import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.cache.impl.DailyAmountCacheImpl;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl extends DailyAmountCacheImpl implements IDailyAmountService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, TestRead.class,new TestReadListener(this)).sheet().doRead();
    }
}