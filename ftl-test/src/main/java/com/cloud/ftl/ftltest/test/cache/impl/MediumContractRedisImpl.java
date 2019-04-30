package com.cloud.ftl.ftltest.test.cache.impl;

import com.cloud.ftl.ftlbasic.redis.BaseRedis;
import com.cloud.ftl.ftltest.test.cache.inft.IMediumContractRedis;
import com.cloud.ftl.ftltest.test.dao.IMediumContractDao;
import com.cloud.ftl.ftltest.test.entity.MediumContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * IMediumContractRedis实现类
 * @author lijun
 */
@Repository("mediumContractRedis")
public class MediumContractRedisImpl extends BaseRedis<String, MediumContract> implements IMediumContractRedis{

    private static final Logger logger = LoggerFactory.getLogger(MediumContractRedisImpl.class);

    @Autowired
    private IMediumContractDao mediumContractDao;

    public final static String PRE_KEY = MediumContract.class.getSimpleName();
    private final static String SEQ_MEDIUMCONTRACT_KEY = "SEQ:".concat(PRE_KEY);
    private final static Long START_MEDIUMCONTRACTID = 1000000L;

   /**
     * 获取MediumContract的ID
     * @return
     */
    @Override
    public Long getMediumContractId(){
        stringRedisTemplate.setEnableTransactionSupport(false);
        return stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            if ( !connection.exists(SEQ_MEDIUMCONTRACT_KEY.getBytes())){
                Long id = mediumContractDao.selectMaxMediumContractId();
                id = ( null == id || id == 0) ?  START_MEDIUMCONTRACTID +  Long.valueOf("1") : ++ id;
                if ( connection.setNX(SEQ_MEDIUMCONTRACT_KEY.getBytes(), String.valueOf(id).getBytes())){
                    return  id;
                }
            }
            return connection.incr(stringRedisTemplate.getStringSerializer().serialize(SEQ_MEDIUMCONTRACT_KEY));
        });
    }

    /**
    * 在Spring容器初始化的时候，初始化该实体ID的最大值。
    * @return
    */
    @PostConstruct
    @Override
    public void initMaxMediumContractId(){
    stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            Long id = 0L;
            if ( !connection.exists(SEQ_MEDIUMCONTRACT_KEY.getBytes())){
                id = mediumContractDao.selectMaxMediumContractId();
                if ( null == id || id == 0){
                    id = START_MEDIUMCONTRACTID;
                }
                connection.setNX(SEQ_MEDIUMCONTRACT_KEY.getBytes(), String.valueOf(id).getBytes());
            }
            return id;
        });
    }
        
}