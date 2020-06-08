package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.cache.impl.ComUserCacheImpl;
import com.cloud.ftl.ftltest.test.service.IComUserService;

/**
 * IComUserService service实现类
 * @author lijun
 */
@Service("comUserService")
public class ComUserServiceImpl extends ComUserCacheImpl implements IComUserService {


}