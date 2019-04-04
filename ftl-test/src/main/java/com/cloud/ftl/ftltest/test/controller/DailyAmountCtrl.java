package com.cloud.ftl.ftltest.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;

/**
 * DailyAmountCtrl 控制层方法
 * @author lijun
 */
@RestController
@RequestMapping("/dailyamount")
public class DailyAmountCtrl{

	@Autowired
    private IDailyAmountService dailyAmountService;


	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//
}
