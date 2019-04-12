package com.cloud.ftl.ftltest.test.cache.inft;

/**
 * 缓存接口类 ILoadTimeRedis
 * @author lijun
 */
public interface ILoadTimeRedis {

   /**
     * 获取LoadTime的ID
     * @return
     */
    Long getLoadTimeId();

    /**
     * 在Spring容器初始化的时候，初始化该实体ID的最大值。
     * @return
     */
    void initMaxLoadTimeId();

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}