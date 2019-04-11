package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.webentity.resp.LoadTimeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

/**
 * LoadTimeCtrl 控制层方法
 * @author lijun
 */
@RestController
@RequestMapping("/loadtime")
public class LoadTimeCtrl{

  @Autowired
  private ILoadTimeService loadTimeService;


  /**
   * LoadTime 根据主键获取单个数据
   * @return
   * @throws Exception
   */
   @GetMapping(value = "/{ltId}")
   public String loadLoadTimeByKey(@PathVariable(value="ltId") Long ltId) throws Exception {
      if(Objects.isNull(ltId)){
         throw new BusiException("请输入要获取的数据的ID") ;
      }
      LoadTime loadTime = loadTimeService.loadLoadTimeByKey(ltId);
      return RespEntity.ok(new LoadTimeResp(loadTime));
   }


	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//
}
