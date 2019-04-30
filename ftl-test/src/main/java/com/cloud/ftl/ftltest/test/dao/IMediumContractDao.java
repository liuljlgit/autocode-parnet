package com.cloud.ftl.ftltest.test.dao;

import com.cloud.ftl.ftltest.test.entity.MediumContract;
import com.cloud.ftl.ftltest.test.query.MediumContractQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
  * 接口类 IMediumContractDao
  * @author lijun
  */
@Repository
public interface IMediumContractDao {

    /**
     * 获取表的最大ID
     * @return
     */
     Long selectMaxMediumContractId();

    /**
     * 根据主键获取对象
     * @param mcId
     * @return
     */
    MediumContract loadMediumContractByKey(Long mcId);

    /**
     * 查询列表
     * @param query
     * @return
     */
    List<MediumContract> findMediumContractList(MediumContractQuery query);

    /**
     * 获取查询总数
     * @param query
     * @return
     */
    Long getTotalMediumContract(MediumContractQuery query);

    /**
     * 新增对象
     * @param mediumContract
     * @return
     */
    Integer addMediumContract(MediumContract mediumContract);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAddMediumContract(List<MediumContract> list);

    /**
     * 更新对象
     * @param mediumContract
     * @return
     */
    Integer updateMediumContract(MediumContract mediumContract);

    /**
     * 批量更新对象
     * @param list
     */
    void batchUpdateMediumContract(List<MediumContract> list);

    /**
     * 更新对象（全更新）
     * @param mediumContract
     * @return
     */
    Integer fullUpdateMediumContract(MediumContract mediumContract);

    /**
     * 批量更新对象（全更新）
     * @param list
     */
    void batchFullUpdateMediumContract(List<MediumContract> list);

    /**
     * 删除对象
     * @param mcId
     * @return
     */
    Integer deleteMediumContract(Long mcId);

    /**
     * 批量删除对象
     * @param list
     */
    void batchDeleteMediumContract(List<Long> list);

   /**
    * 根据ID列表从数据库中查询列表
    * @param list
    * @return
    */
    List<MediumContract> findMediumContractByIdList(List<Long> list);

    //------------------------ custom code begin ------------------------//
        
    //======================== custom code end ========================//

}