package com.cloud.ftl.ftltest.test.service.inft;

import java.util.List;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import com.cloud.ftl.ftltest.test.query.LoadTimeQuery;

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

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<LoadTime> findLoadTimeList(LoadTimeQuery query) throws Exception;
}
