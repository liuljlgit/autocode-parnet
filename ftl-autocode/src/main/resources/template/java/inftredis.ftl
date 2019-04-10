package ${inftRedisPackagePath};

/**
 * 缓存接口类 I${className}Redis
 * @author lijun
 */
public interface I${className}Redis {

   /**
     * 获取${className}的ID
     * @return
     */
    Long get${className}Id();

    /**
     * 在Spring容器初始化的时候，初始化该实体ID的最大值。
     * @return
     */
    void initMax${className}Id();

}