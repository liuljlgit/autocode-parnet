package com.cloud.ftl.ftltest.test.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;

/**
 * ILoadTimeService service实现类
 * @author lijun
 */
@Service("loadTimeService")
public class LoadTimeServiceImpl implements ILoadTimeService {

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     * @throws Exception
     */
    @Override
    public LoadTime loadLoadTimeByKey(Long ltId) throws Exception {
        return null;
    }
}