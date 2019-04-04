package com.cloud.ftl.ftltest.test.service.inft;

import com.cloud.ftl.ftltest.test.entity.LoadTime;

/**
 * ILoadTimeService service接口类
 * @author lijun
 */
public interface ILoadTimeService {

    /**
     * 根据主键获取对象
     * @param ltId
     * @return
     * @throws Exception
     */
   LoadTime loadLoadTimeByKey(Long ltId) throws Exception;
}
