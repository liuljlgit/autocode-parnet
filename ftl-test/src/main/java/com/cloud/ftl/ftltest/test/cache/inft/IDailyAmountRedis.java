package com.cloud.ftl.ftltest.test.cache.inft;

/**
 * 缓存接口类 IDailyAmountRedis
 * @author lijun
 */
public interface IDailyAmountRedis {

   /**
     * 获取DailyAmount的ID
     * @return
     */
    Long getDailyAmountId();

    /**
     * 在Spring容器初始化的时候，初始化该实体ID的最大值。
     * @return
     */
    void initMaxDailyAmountId();

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}