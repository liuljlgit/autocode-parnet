package com.cloud.ftl.ftltest.test.cache.impl;

import com.cloud.ftl.ftlbasic.redis.BaseRedis;
import com.cloud.ftl.ftltest.test.cache.inft.IDailyAmountRedis;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * IDailyAmountRedis实现类
 * @author lijun
 */
@Repository("dailyAmountRedis")
public class DailyAmountRedisImpl extends BaseRedis<String, DailyAmount> implements IDailyAmountRedis{

    private static final Logger logger = LoggerFactory.getLogger(DailyAmountRedisImpl.class);

    @Autowired
    private IDailyAmountDao dailyAmountDao;

    public final static String PRE_KEY = DailyAmount.class.getSimpleName();
    private final static String SEQ_DAILYAMOUNT_KEY = "SEQ:".concat(PRE_KEY);
    private final static Long START_DAILYAMOUNTID = 1000000L;

   /**
     * 获取DailyAmount的ID
     * @return
     */
    @Override
    public Long getDailyAmountId(){
        stringRedisTemplate.setEnableTransactionSupport(false);
        return stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            if ( !connection.exists(SEQ_DAILYAMOUNT_KEY.getBytes())){
                Long id = dailyAmountDao.selectMaxDailyAmountId();
                id = ( null == id || id == 0) ?  START_DAILYAMOUNTID +  Long.valueOf("1") : ++ id;
                if ( connection.setNX(SEQ_DAILYAMOUNT_KEY.getBytes(), String.valueOf(id).getBytes())){
                    return  id;
                }
            }
            return connection.incr(stringRedisTemplate.getStringSerializer().serialize(SEQ_DAILYAMOUNT_KEY));
        });
    }

    /**
    * 在Spring容器初始化的时候，初始化该实体ID的最大值。
    * @return
    */
    @PostConstruct
    @Override
    public void initMaxDailyAmountId(){
    stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            Long id = 0L;
            if ( !connection.exists(SEQ_DAILYAMOUNT_KEY.getBytes())){
                id = dailyAmountDao.selectMaxDailyAmountId();
                if ( null == id || id == 0){
                    id = START_DAILYAMOUNTID;
                }
                connection.setNX(SEQ_DAILYAMOUNT_KEY.getBytes(), String.valueOf(id).getBytes());
            }
            return id;
        });
    }
        
}