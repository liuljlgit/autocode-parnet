package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.util.CollectionUtils;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftltest.test.entity.MediumContract;
import com.cloud.ftl.ftltest.test.service.inft.IMediumContractService;
import com.cloud.ftl.ftltest.test.dao.IMediumContractDao;
import com.cloud.ftl.ftltest.test.query.MediumContractQuery;
import com.cloud.ftl.ftltest.test.cache.inft.IMediumContractRedis;
import com.cloud.ftl.ftltest.test.webentity.resp.MediumContractResp;

/**
 * IMediumContractService service实现类
 * @author lijun
 */
@Service("mediumContractService")
public class MediumContractServiceImpl implements IMediumContractService {

    private static final Logger logger = LoggerFactory.getLogger(MediumContractServiceImpl.class);

    @Autowired
    private IMediumContractDao mediumContractDao;
    @Autowired
    private IMediumContractRedis mediumContractRedis;

    /**
     * 根据主键获取对象
     * @param mcId
     * @return
     * @throws Exception
     */
    @Override
    public MediumContract loadMediumContractByKey(Long mcId) throws Exception {
        if(Objects.isNull(mcId)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        MediumContract mediumContract = mediumContractDao.loadMediumContractByKey(mcId);
        if(Objects.isNull(mediumContract)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return mediumContract;
    }

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public MediumContract selectOneMediumContract(MediumContractQuery query) throws Exception {
        List<MediumContract> list = findMediumContractList(query);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<MediumContractResp> getMediumContractPageList(MediumContractQuery query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = mediumContractDao.getTotalMediumContract(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<MediumContractResp> mediumContractList = findMediumContractList(query).stream().map(MediumContractResp::new).collect(Collectors.toList());
        return new PageBean<>(totalPage,total,mediumContractList);
    }


    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<MediumContract> findMediumContractList(MediumContractQuery query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return mediumContractDao.findMediumContractList(query);
    }

    /**
     * 新增对象
     * @param mediumContract
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addMediumContract(MediumContract mediumContract) throws Exception {
        if(Objects.isNull(mediumContract)){
            return 0;
        }
        if(Objects.isNull(mediumContract.getMcId())){
            mediumContract.setMcId(mediumContractRedis.getMediumContractId());
        }
        return mediumContractDao.addMediumContract(mediumContract);
    }

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddMediumContract(List<MediumContract> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (MediumContract mediumContract : list) {
            if(Objects.isNull(mediumContract.getMcId())){
                mediumContract.setMcId(mediumContractRedis.getMediumContractId());
            }
        }
        mediumContractDao.batchAddMediumContract(list);
    }

    /**
     * 更新对象
     * @param mediumContract
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateMediumContract(MediumContract mediumContract,Boolean fullUpdate) throws Exception {
        if(Objects.isNull(mediumContract)){
            return 0;
        }
        if(Objects.isNull(mediumContract.getMcId())){
            throw new BusiException("主键不能为空");
        }
        Integer result;
        if(fullUpdate){
            result = mediumContractDao.fullUpdateMediumContract(mediumContract);
        } else {
            result = mediumContractDao.updateMediumContract(mediumContract);
        }
        return result;
    }

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateMediumContract(List<MediumContract> list,Boolean fullUpdate) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (MediumContract mediumContract : list) {
            if(Objects.isNull(mediumContract.getMcId())){
                throw new BusiException("主键不能为空");
            }
        }
        if(fullUpdate){
            mediumContractDao.batchFullUpdateMediumContract(list);
        } else {
            mediumContractDao.batchUpdateMediumContract(list);
        }
    }

    /**
     * 删除对象
     * @param mcId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteMediumContract(Long mcId) throws Exception {
        if(Objects.isNull(mcId)){
            throw new BusiException("主键不能为空");
        }
        return mediumContractDao.deleteMediumContract(mcId);
    }

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteMediumContract(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        mediumContractDao.batchDeleteMediumContract(list);
    }

    /**
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public List<MediumContract> findMediumContractByIdList(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        return mediumContractDao.findMediumContractByIdList(list);
    }

    /**
     * 保存记录
     * @param mediumContract
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMediumContract(MediumContract mediumContract) throws Exception {
        if(Objects.isNull(mediumContract)){
           return ;
        }
        if(Objects.isNull(mediumContract.getMcId())){
            mediumContract.setMcId(mediumContractRedis.getMediumContractId());
            addMediumContract(mediumContract);
        }else{
            updateMediumContract(mediumContract,false);
        }
    }

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMediumContractList(List<MediumContract> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        List<MediumContract> addList = list.stream().filter(e -> Objects.isNull(e.getMcId())).collect(Collectors.toList());
        List<MediumContract> updateList = list.stream().filter(e -> Objects.nonNull(e.getMcId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(addList)){
            addList = addList.stream().map(e->{
                e.setMcId(mediumContractRedis.getMediumContractId());
                return e;
            }).collect(Collectors.toList());
            batchAddMediumContract(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdateMediumContract(updateList,false);
        }
    }

}