package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl extends BaseServiceImpl<LoadTime> implements ILoadTimeService {


}