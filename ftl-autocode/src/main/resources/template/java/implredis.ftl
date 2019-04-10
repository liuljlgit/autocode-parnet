package ${implRedisPackagePath};

import com.cloud.ftl.ftlbasic.redis.BaseRedis;
import ${inftRedisPackagePath}.I${className}Redis;
import ${daoPackagePath}.I${className}Dao;
import ${entityPackagePath}.${className};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * I${className}Redis实现类
 * @author lijun
 */
@Repository("${objectName}Redis")
public class ${className}RedisImpl extends BaseRedis<String, ${className}> implements I${className}Redis{

    private static final Logger logger = LoggerFactory.getLogger(${className}RedisImpl.class);

    @Autowired
    private I${className}Dao ${objectName}Dao;

    public final static String PRE_KEY = ${className}.class.getSimpleName();
    private final static String SEQ_${className?upper_case}_KEY = "SEQ:".concat(PRE_KEY);
    private final static Long START_${className?upper_case}ID = 1000000L;

   /**
     * 获取${className}的ID
     * @return
     */
    @Override
    public Long get${className}Id(){
        stringRedisTemplate.setEnableTransactionSupport(false);
        return stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            if ( !connection.exists(SEQ_${className?upper_case}_KEY.getBytes())){
                Long id = ${objectName}Dao.selectMax${className}Id();
                id = ( null == id || id == 0) ?  START_${className?upper_case}ID +  Long.valueOf("1") : ++ id;
                if ( connection.setNX(SEQ_${className?upper_case}_KEY.getBytes(), String.valueOf(id).getBytes())){
                    return  id;
                }
            }
            return connection.incr(stringRedisTemplate.getStringSerializer().serialize(SEQ_${className?upper_case}_KEY));
        });
    }

    /**
    * 在Spring容器初始化的时候，初始化该实体ID的最大值。
    * @return
    */
    @PostConstruct
    @Override
    public void initMax${className}Id(){
    stringRedisTemplate.execute((RedisCallback<Long>) connection->{
            Long id = 0L;
            if ( !connection.exists(SEQ_${className?upper_case}_KEY.getBytes())){
                id = ${objectName}Dao.selectMax${className}Id();
                if ( null == id || id == 0){
                    id = START_${className?upper_case}ID;
                }
                connection.setNX(SEQ_${className?upper_case}_KEY.getBytes(), String.valueOf(id).getBytes());
            }
            return id;
        });
    }

}