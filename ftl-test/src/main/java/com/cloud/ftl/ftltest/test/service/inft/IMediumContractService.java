package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.entity.MediumContract;
import com.cloud.ftl.ftltest.test.query.MediumContractQuery;
import com.cloud.ftl.ftltest.test.webentity.resp.MediumContractResp;

/**
 * IMediumContractService service接口类
 * @author lijun
 */
public interface IMediumContractService {

    /**
     * 根据主键获取对象
     * @param mcId
     * @return
     * @throws Exception
     */
    MediumContract loadMediumContractByKey(Long mcId) throws Exception;

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    MediumContract selectOneMediumContract(MediumContractQuery query) throws Exception;

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<MediumContractResp> getMediumContractPageList(MediumContractQuery query) throws Exception;

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<MediumContract> findMediumContractList(MediumContractQuery query) throws Exception;

    /**
     * 新增对象
     * @param mediumContract
     * @return
     * @throws Exception
     */
    Integer addMediumContract(MediumContract mediumContract) throws Exception;

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    void batchAddMediumContract(List<MediumContract> list) throws Exception;

    /**
     * 更新对象
     * @param mediumContract
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    Integer updateMediumContract(MediumContract mediumContract,Boolean fullUpdate) throws Exception;

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    void batchUpdateMediumContract(List<MediumContract> list,Boolean fullUpdate) throws Exception;

    /**
     * 删除对象
     * @param mcId
     * @return
     * @throws Exception
     */
    Integer deleteMediumContract(Long mcId) throws Exception;

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    void batchDeleteMediumContract(List<Long> list) throws Exception;

    /**
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    List<MediumContract> findMediumContractByIdList(List<Long> list) throws Exception;

    /**
     * 保存记录
     * @param mediumContract
     * @throws Exception
     */
     void saveMediumContract(MediumContract mediumContract) throws Exception;

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
     void saveMediumContractList(List<MediumContract> list) throws Exception;

}
