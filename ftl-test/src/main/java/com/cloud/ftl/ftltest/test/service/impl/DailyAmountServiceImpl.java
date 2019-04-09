package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl implements IDailyAmountService {

    private static final Logger logger = LoggerFactory.getLogger(DailyAmountServiceImpl.class);

    @Autowired
    private IDailyAmountDao dailyAmountDao;

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     * @throws Exception
     */
    @Override
    public DailyAmount loadDailyAmountByKey(Long daId) throws Exception {
        if(Objects.isNull(daId)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        DailyAmount dailyAmount = dailyAmountDao.loadDailyAmountByKey(daId);
        if(Objects.isNull(dailyAmount)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return dailyAmount;
    }
}