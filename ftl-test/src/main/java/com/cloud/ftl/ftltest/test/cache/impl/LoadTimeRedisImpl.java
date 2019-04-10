package com.cloud.ftl.ftltest.test.cache.impl;

import com.cloud.ftl.ftlbasic.redis.BaseRedis;
import com.cloud.ftl.ftltest.test.cache.inft.ILoadTimeRedis;
import com.cloud.ftl.ftltest.test.dao.ILoadTimeDao;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * ILoadTimeRedis实现类
 * @author lijun
 */
@Repository("loadTimeRedis")
public class LoadTimeRedisImpl extends BaseRedis<String, LoadTime> implements ILoadTimeRedis{

    private static final Logger logger = LoggerFactory.getLogger(LoadTimeRedisImpl.class);

    @Autowired
    private ILoadTimeDao loadTimeDao;

    public final static String PRE_KEY = LoadTime.class.getSimpleName();
    private final static String SEQ_LOADTIME_KEY = "SEQ:".concat(PRE_KEY);
    private final static Long START_LOADTIMEID = 1000000L;

   /**
     * 获取LoadTime的ID
     * @return
     */
    @Override
    public Long getLoadTimeId(){
        stringRedisTemplate.setEnableTransactionSupport(false);
        return stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            if ( !connection.exists(SEQ_LOADTIME_KEY.getBytes())){
                Long id = loadTimeDao.selectMaxLoadTimeId();
                id = ( null == id || id == 0) ?  START_LOADTIMEID +  Long.valueOf("1") : ++ id;
                if ( connection.setNX(SEQ_LOADTIME_KEY.getBytes(), String.valueOf(id).getBytes())){
                    return  id;
                }
            }
            return connection.incr(stringRedisTemplate.getStringSerializer().serialize(SEQ_LOADTIME_KEY));
        });
    }

    /**
    * 在Spring容器初始化的时候，初始化该实体ID的最大值。
    * @return
    */
    @PostConstruct
    @Override
    public void initMaxLoadTimeId(){
    stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            Long id = 0L;
            if ( !connection.exists(SEQ_LOADTIME_KEY.getBytes())){
                id = loadTimeDao.selectMaxLoadTimeId();
                if ( null == id || id == 0){
                    id = START_LOADTIMEID;
                }
                connection.setNX(SEQ_LOADTIME_KEY.getBytes(), String.valueOf(id).getBytes());
            }
            return id;
        });
    }

}