package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.webentity.resp.DailyAmountResp;
import com.cloud.ftl.ftltest.test.webentity.req.DailyAmountReq;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * DailyAmountCtrl 控制层方法
 * @author lijun
 */
@RestController
@RequestMapping("/dailyamount")
public class DailyAmountCtrl{

  @Autowired
  private IDailyAmountService dailyAmountService;


    /**
    * DailyAmount 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/{daId}")
    public String loadDailyAmountByKey(@PathVariable(value="daId") Long daId) throws Exception {
        if(Objects.isNull(daId)){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        DailyAmount dailyAmount = dailyAmountService.loadDailyAmountByKey(daId);
        return RespEntity.ok(new DailyAmountResp(dailyAmount));
    }

   /**
    * DailyAmount 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/list")
    public String getDailyAmountPageList(@RequestBody DailyAmountReq dailyAmountReq) throws Exception {
        DailyAmountQuery query = BeanUtil.createBean(dailyAmountReq, DailyAmountQuery.class);
        PageBean<DailyAmount> pageList = dailyAmountService.getDailyAmountPageList(query);
        return RespEntity.ok(pageList);
    }


	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//
}
