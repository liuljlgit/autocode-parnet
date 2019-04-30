package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftltest.test.service.inft.IMediumContractService;
import com.cloud.ftl.ftltest.test.entity.MediumContract;
import com.cloud.ftl.ftltest.test.webentity.resp.MediumContractResp;
import com.cloud.ftl.ftltest.test.webentity.req.MediumContractReq;
import com.cloud.ftl.ftltest.test.query.MediumContractQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * MediumContractCtrl 控制层方法
 * @author lijun
 */
@RestController
public class MediumContractCtrl{

  @Autowired
  private IMediumContractService mediumContractService;


    /**
    * MediumContract 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/mediumcontract/{mcId}")
    public CommonResp<MediumContractResp> loadMediumContractByKey(@PathVariable(value="mcId") Long mcId) throws Exception {
        if(Objects.isNull(mcId)){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        MediumContract mediumContract = mediumContractService.loadMediumContractByKey(mcId);
        return RespEntity.ok(new MediumContractResp(mediumContract));
    }

   /**
    * MediumContract 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/mediumcontract/list")
    public CommonResp<PageBean<MediumContractResp>> getMediumContractPageList(@RequestBody MediumContractReq mediumContractReq) throws Exception {
        MediumContractQuery query = BeanUtil.createBean(mediumContractReq, MediumContractQuery.class);
        PageBean<MediumContractResp> pageList = mediumContractService.getMediumContractPageList(query);
        return RespEntity.ok(pageList);
    }

    /**
    * MediumContract 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/mediumcontract")
    public CommonResp<Object> saveMediumContract(@RequestBody MediumContractReq mediumContractReq) throws  Exception{
        MediumContract mediumContract = BeanUtil.createBean(mediumContractReq, MediumContract.class);
        mediumContractService.saveMediumContract(mediumContract);
        return RespEntity.ok();
    }

    /**
    * MediumContract 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/mediumcontract/{mcId}")
    public CommonResp<Object> deleteMediumContract(@PathVariable(value="mcId") Long mcId) throws  Exception{
        if(Objects.isNull(mcId)){
           throw new BusiException("删除主键不可为空") ;
        }
        mediumContractService.deleteMediumContract(mcId);
        return RespEntity.ok();
    }

}
