package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;

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

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    DailyAmount selectOneDailyAmount(DailyAmountQuery query) throws Exception;

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<DailyAmount> getDailyAmountPageList(DailyAmountQuery query) throws Exception;

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<DailyAmount> findDailyAmountList(DailyAmountQuery query) throws Exception;
}
