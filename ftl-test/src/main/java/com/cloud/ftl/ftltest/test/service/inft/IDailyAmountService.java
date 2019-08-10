package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.service.IBaseService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

/**
 * IDailyAmountService service接口类
 * @author lijun
 */
public interface IDailyAmountService extends IBaseService<DailyAmount>{

    /**
     * 新增对象
     * @param dailyAmount
     * @return
     * @throws Exception
     */
    Integer addDailyAmount(DailyAmount dailyAmount) throws Exception;

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    void batchAddDailyAmount(List<DailyAmount> list) throws Exception;

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    void batchUpdateDailyAmount(List<DailyAmount> list,Boolean fullUpdate) throws Exception;

    /**
     * 删除对象
     * @param daId
     * @return
     * @throws Exception
     */
    Integer deleteDailyAmount(Long daId) throws Exception;

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    void batchDeleteDailyAmount(List<Long> list) throws Exception;

    /**
     * 保存记录
     * @param dailyAmount
     * @throws Exception
     */
     void saveDailyAmount(DailyAmount dailyAmount) throws Exception;

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
     void saveDailyAmountList(List<DailyAmount> list) throws Exception;

}
