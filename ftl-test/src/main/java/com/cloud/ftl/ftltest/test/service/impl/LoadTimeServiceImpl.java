package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.cache.impl.LoadTimeCacheImpl;
import com.cloud.ftl.ftltest.test.service.ILoadTimeService;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl extends LoadTimeCacheImpl implements ILoadTimeService {


}