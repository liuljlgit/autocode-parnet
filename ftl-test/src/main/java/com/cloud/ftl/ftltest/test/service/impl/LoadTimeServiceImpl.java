package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftlbasic.service.AbstractBaseService;
import org.springframework.data.redis.core.RedisTemplate;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl extends AbstractBaseService<LoadTime> implements ILoadTimeService {

    public LoadTimeServiceImpl(ILoadTimeDao loadTimeDao,RedisTemplate<String,String> stringRedisTemplate){
        super(loadTimeDao,stringRedisTemplate);
    }

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    private ILoadTimeDao loadTimeDao;

    //自定义Service方法


}