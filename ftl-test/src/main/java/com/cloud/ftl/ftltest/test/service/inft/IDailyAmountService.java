package com.cloud.ftl.ftltest.test.service.inft;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;

import java.util.List;

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
     * 更新对象
     * @param dailyAmount
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    Integer updateDailyAmount(DailyAmount dailyAmount,Boolean fullUpdate) throws Exception;

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
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    List<DailyAmount> findDailyAmountByIdList(List<Long> list) throws Exception;

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

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}
