package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      return RespEntity.ok(dailyAmount);
   }


	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//
}
