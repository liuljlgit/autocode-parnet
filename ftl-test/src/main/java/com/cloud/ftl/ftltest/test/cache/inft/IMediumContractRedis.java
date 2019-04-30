package com.cloud.ftl.ftltest.test.cache.inft;

/**
 * 缓存接口类 IMediumContractRedis
 * @author lijun
 */
public interface IMediumContractRedis {

   /**
     * 获取MediumContract的ID
     * @return
     */
    Long getMediumContractId();

    /**
     * 在Spring容器初始化的时候，初始化该实体ID的最大值。
     * @return
     */
    void initMaxMediumContractId();

}