package com.cloud.ftl.ftltest.test.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;

/**
  * 接口类 IDailyAmountDao
  * @author lijun
  */
@Repository
public interface IDailyAmountDao {

    /**
     * 获取表的最大ID
     * @return
     */
     Long selectMaxDailyAmountId();

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     */
    DailyAmount loadDailyAmountByKey(Long daId);

    /**
     * 查询列表
     * @param query
     * @return
     */
    List<DailyAmount> findDailyAmountList(@Param("query") DailyAmountQuery query);

    /**
     * 获取查询总数
     * @param query
     * @return
     */
    Long getTotalDailyAmount(@Param("query") DailyAmountQuery query);

    /**
     * 新增对象
     * @param dailyAmount
     * @return
     */
    Integer addDailyAmount(DailyAmount dailyAmount);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAddDailyAmount(List<DailyAmount> list);

    /**
     * 更新对象
     * @param dailyAmount
     * @return
     */
    Integer updateDailyAmount(DailyAmount dailyAmount);

    /**
     * 批量更新对象
     * @param list
     */
    void batchUpdateDailyAmount(List<DailyAmount> list);

    /**
     * 批量更新对象
     * @param params
     * @param query
     */
    void batchUpdateDailyAmountByQuery(@Param("params") Map<String,Object> params,@Param("query") DailyAmountQuery query);

    /**
     * 更新对象（全更新）
     * @param dailyAmount
     * @return
     */
    Integer fullUpdateDailyAmount(DailyAmount dailyAmount);

    /**
     * 批量更新对象（全更新）
     * @param list
     */
    void batchFullUpdateDailyAmount(List<DailyAmount> list);

    /**
     * 删除对象
     * @param daId
     * @return
     */
    Integer deleteDailyAmount(Long daId);

    /**
     * 批量删除对象
     * @param list
     */
    void batchDeleteDailyAmount(List<Long> list);

   /**
    * 批量删除对象
    * @param query
    */
    void batchDeleteDailyAmountByQuery(@Param("query") DailyAmountQuery query);

   /**
    * 根据ID列表从数据库中查询列表
    * @param list
    * @return
    */
    List<DailyAmount> findDailyAmountByIdList(List<Long> list);

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}