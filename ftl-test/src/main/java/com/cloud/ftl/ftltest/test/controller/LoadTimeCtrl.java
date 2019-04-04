package com.cloud.ftl.ftltest.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;

/**
 * LoadTimeCtrl 控制层方法
 * @author lijun
 */
@RestController
@RequestMapping("/loadtime")
public class LoadTimeCtrl{

	@Autowired
    private ILoadTimeService loadTimeService;


	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//
}
