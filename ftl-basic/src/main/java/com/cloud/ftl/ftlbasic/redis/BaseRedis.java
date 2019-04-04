package com.cloud.ftl.ftlbasic.redis;

import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.*;

public abstract class BaseRedis<K, V> {
    private Logger logger = LoggerFactory.getLogger(BaseRedis.class);
    @Autowired
    protected RedisTemplate<K, V> stringRedisTemplate;
    @Autowired
    protected RedisTemplate<K, V> pojoRedisTemplate;

    /**
     * 原子操作，不用事务控制，出错了再逆向操作
     * @param key
     * @return
     */
    protected Long incr(final String key) {
        Long result = getRedisTemplate(true).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            return connection.incr(byteKey);
        });
        return result;
    }

    /**
     *
     * @param key
     * @return
     */
    protected Long decr(final String key) {
        Long result = getRedisTemplate(true).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            return connection.decr(byteKey);
        });
        return result;
    }

    /**
     * 原子操作，不用事务控制，出错了在逆向操作
     * @param key
     * @return
     */
    protected Long incrBy(final String key, Long totalValue) {
        Long result = getRedisTemplate(true).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            return connection.incrBy(byteKey, totalValue.longValue()) ;
        });
        return result;
    }

    /**
     *
     * @param key
     * @return
     */
    protected Long decrBy(final String key, Long totalValue) {
        Long result = getRedisTemplate(true).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            return connection.decrBy(byteKey, totalValue.longValue()) ;

        });
        return result;
    }


    /**
     * 设置一个结构数据
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    protected boolean set(final String key, final String value, final long seconds) {
        return set(key, value, seconds, false) ;
    }

    /**
     *
     * @param key
     * @param value
     * @param seconds
     * @param suppressTran 是否压制事务，true，表示在事务环境中立即执行（不进redis队列）
     * @return
     */
    protected boolean set(final String key, final String value, final long seconds, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = serializer.serialize(value);
            if (seconds > 0) {
                connection.setEx(byteKey, seconds, byteValue);
            } else {
                connection.set(byteKey, byteValue);
            }
            return true;
        });
        return result;
    }

    /**
     * 设置一个结构数据(主要用于保存bean对象，初始化incr值请使用String类型的方法)
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    protected boolean set(final String key, final Serializable value, final long seconds) {
        return set(key, value, seconds, false) ;
    }

    /**
     * 设置一个结构数据(主要用于保存bean对象，初始化incr值请使用String类型的方法)
     *
     * @param key
     * @param value
     * @param seconds
     * @param suppressTran 是否压制事务，true，表示在事务环境中立即执行（不进redis队列）
     * @return
     */
    protected boolean set(final String key, final Serializable value, final long seconds, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            if (seconds > 0) {
                connection.setEx(byteKey, seconds, byteValue);
            } else {
                connection.set(byteKey, byteValue);
            }
            return true;
        });
        return result;
    }

    /**
     * 根据Key获取对象
     *
     * @param key
     * @return
     */
    protected Object get(final String key) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] value = connection.get(byteKey);
            if (value == null) {
                return null;
            }
            Object dataObj = serializer.deserialize(value);
            return dataObj;
        });
        return result;
    }

    /**
     * 根据Key获取对象
     *
     * @param key
     * @return
     */
    protected <T extends Serializable> T get(final String key, final Class<T> clazz) {
        T result = getRedisTemplate(true).execute((RedisCallback<T>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] value = connection.get(byteKey);
            if (value == null) {
                return null;
            }
            T dataObj = CommonUtil.transByteArray2Obj(value, clazz);
            return dataObj;
        });
        return result;
    }

    protected Object mget(final List<String> keys) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            List<byte[]> listByteKeys = new ArrayList<byte[]>();
            for (String key : keys) {
                byte[] byteKey = serializer.serialize(key);
                listByteKeys.add(byteKey);
            }

            List<byte[]> listByteValues = connection.mGet(listByteKeys.toArray(new byte[listByteKeys.size()][]));
            List<Object> listValue = new ArrayList<Object>();
            for (byte[] value : listByteValues) {
                listValue.add(serializer.deserialize(value));
            }
            return listValue;
        });
        return result;
    }

    /**
     * @param argKey
     * @return
     */
    protected Object keys(final String argKey) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(argKey);
            Set<byte[]> setBytes = connection.keys(byteKey);
            List<Object> listValue = new ArrayList<Object>();
            for (byte[] value : setBytes) {
                listValue.add(serializer.deserialize(value));
            }
            return listValue;
        });
        return result;
    }

    protected <T extends Serializable> List<T> mget(final List<String> keys, final Class<T> clazz) {
        List<T> result = getRedisTemplate(true).execute((RedisCallback<List<T>>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            List<byte[]> listByteKeys = new ArrayList<byte[]>();
            for (String key : keys) {
                byte[] byteKey = serializer.serialize(key);
                listByteKeys.add(byteKey);
            }

            List<byte[]> listByteValues = connection.mGet(listByteKeys.toArray(new byte[listByteKeys.size()][]));
            List<T> listValue = new ArrayList<T>();
            for (byte[] value : listByteValues) {
                listValue.add(CommonUtil.transByteArray2Obj(value, clazz));
            }
            return listValue;
        });
        return result;
    }

    protected boolean exists(final String key) {
        boolean result = getRedisTemplate(true).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            return connection.exists(byteKey);
        });
        return result;
    }

    protected Long ttl(final String key) {
        Long result = getRedisTemplate(true).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);

            return connection.ttl(byteKey);
        });
        return result;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////// Set 操作接口 //////////////////////////
    /////////////////////////////////////////////////////////////////
    protected boolean sadd(final String key, final String value) {
        return sadd(key, value, false);
    }
    /**
     *
     * @param key
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean sadd(final String key, final String value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = serializer.serialize(value);
            connection.sAdd(byteKey, byteValue);
            return true;
        });
        return result;
    }
    /**
     *
     * @param key
     * @param value
     * @return
     */
    protected boolean sadd(final String key, final Serializable value) {
        return sadd(key, value, false);
    }

    /**
     * 仅限于基本对象使用，Bean对象禁止使用
     * @param key
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean sadd(final String key, final Serializable value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            connection.sAdd(byteKey, byteValue);
            return true;
        });
        return result;
    }


    protected boolean sadd(final String key, final Collection<? extends Serializable> values) {
        return sadd(key, values, false) ;
    }

    /**
     *
     * @param key
     * @param values
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean sadd(final String key, final Collection<? extends Serializable> values, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[][] byteValues = new byte[values.size()][];
            int i = 0;
            for (Serializable v : values) {
                byteValues[i++] = CommonUtil.transObj2ByteArray(v);
            }
            connection.sAdd(byteKey, byteValues);
            return true;
        });
        return result;
    }

    protected Long scard(final String key) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Long count = connection.sCard(byteKey);
            return count;
        });
        return Long.parseLong(String.valueOf(result));
    }

    @SuppressWarnings("rawtypes")
    protected Object smembers(final String key) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Set setValue = connection.sMembers(byteKey);

            Iterator iter = setValue.iterator();
            List<Object> listObj = new ArrayList<Object>();
            while (iter.hasNext()) {
                Object obj = serializer.deserialize((byte[]) iter.next());
                listObj.add(obj);
            }
            return listObj;
        });
        return result;
    }

    protected <T extends Serializable> Set<T> smembers(final String key, final Class<T> clazz) {
        Set<T> result = getRedisTemplate(true).execute((RedisCallback<Set<T>>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Set<byte[]> setValue = connection.sMembers(byteKey);
            Iterator<byte[]> iter = setValue.iterator();
            Set<T> result1 = new HashSet<T>();
            while (iter.hasNext()) {
                T tmp = CommonUtil.transByteArray2Obj(iter.next(), clazz);
                result1.add(tmp);
            }
            if (result1.size() == 0) {
                return null;
            }
            return result1;
        });
        return result;
    }

    protected Set<String> smembersString(final String key) {
        Set<String> result = getRedisTemplate(true).execute((RedisCallback<Set<String>>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Set<byte[]> setValue = connection.sMembers(byteKey);
            Iterator<byte[]> iter = setValue.iterator();
            Set<String> setStr = new HashSet<String>();
            while (iter.hasNext()) {
                String obj = serializer.deserialize(iter.next());
                setStr.add(obj);
            }
            return setStr;
        });
        return result;
    }


    protected boolean srem(final String key, final String value) {
        return srem(key, value, false);
    }
    /**
     * 移出set中指定元素，在事务中无法返回结果，若业务需要判断移出是否成功，请使用smember判断
     * @param key
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean srem(final String key, final String value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = serializer.serialize(value);
            Long index = connection.sRem(byteKey, byteValue);
            return index == null ? false : index > 0L;
        });
        return result;
    }

    protected boolean srem(final String key, final Serializable value) {
        return srem(key, value, false);
    }

    /**
     *
     * @param key
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean srem(final String key, final Serializable value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            Long index = connection.sRem(byteKey, byteValue);
            return index == null ? false : index > 0L;
        });
        return result;

    }

    protected boolean srem(final String key, final List<String> values) {
        return srem(key, values, false) ;
    }

    /**
     *
     * @param key
     * @param values
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean srem(final String key, final List<String> values, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[][] byteValues = new byte[values.size()][];
            for (int i = 0; i < values.size(); i++) {
                byteValues[i] = serializer.serialize(values.get(i));
            }
            Long index = connection.sRem(byteKey, byteValues);
            return true;
        });
        return result;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////// ZSet接口//////////////////////////////
    /////////////////////////////////////////////////////////////////
    protected boolean zadd(final String key, final Double score, final String value) {
        return zadd(key, score, value, false) ;
    }

    /**
     *
     * @param key
     * @param score
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean zadd(final String key, final Double score, final String value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = serializer.serialize(value);
            connection.zAdd(byteKey, score, byteValue);
            return true;
        });
        return result;
    }
    /**
     *
     * @param key
     * @param value
     * @return
     */
    protected boolean zrem(final String key, final String value) {
        return zrem(key, value, false);
    }
    /**
     *
     * @param key
     * @param value
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean zrem(final String key, final String value, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = serializer.serialize(value);
            connection.zRem(byteKey, byteValue);
            return true;
        });
        return result;
    }

    protected Object zrange(final String key, final Long begin, final Long end) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);

            Set<byte[]> setValue = connection.zRange(byteKey, begin, end);
            Iterator<byte[]> iter = setValue.iterator();
            List<Object> listObj = new ArrayList<>();
            while (iter.hasNext()) {
                Object obj = serializer.deserialize(iter.next());
                listObj.add(obj);
            }
            return listObj;
        });
        return result;
    }

    @SuppressWarnings("rawtypes")
    protected Object zrangebyscore(final String key, final Double begin, final Double end) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);

            Set setValue = connection.zRangeByScore(byteKey, begin, end);
            Iterator iter = setValue.iterator();
            List<Object> listObj = new ArrayList<Object>();
            while (iter.hasNext()) {
                Object obj = serializer.deserialize((byte[]) iter.next());
                listObj.add(obj);
            }
            return listObj;
        });
        return result;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////// HSet接口//////////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param key
     * @param field
     * @param value
     * @param seconds
     * @return
     */
    protected boolean hset(final String key, final String field, final Serializable value, final Long seconds) {
        return hset(key, field, value,seconds, false) ;
    }

    /**
     *
     * @param key
     * @param field
     * @param value
     * @param seconds
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean hset(final String key, final String field, final Serializable value, final Long seconds, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            connection.hSet(byteKey, byteField, byteValue);
            if (seconds.equals(0L)) {
                connection.persist(byteKey);
            } else {
                connection.expire(byteKey, seconds);// 设置过期时间
            }
            return true;
        });
        return result;
    }

    protected Object hget(final String key, final String field) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            byte[] value = connection.hGet(byteKey, byteField);
            if (value == null) {
                logger.error("未找到redis中保存的数据" + new String(byteKey));
                return null;
            }
            Object dataObj = serializer.deserialize(value);
            return dataObj;
        });
        return result;
    }

    protected <T extends Serializable> T hget(final String key, final String field, final Class<T> clazz) {
        T result = getRedisTemplate(true).execute((RedisCallback<T>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            byte[] value = connection.hGet(byteKey, byteField);
            if (value == null) {

                return null;
            }
            T dataObj = CommonUtil.transByteArray2Obj(value, clazz);
            return dataObj;
        });
        return result;
    }

    /**
     *
     * @param key
     * @param field
     * @param incrBy
     * @return
     */
    protected Long hincrBy(final String key, final String field, Long incrBy) {
        return hincrBy(key, field, incrBy, false);
    }

    /**
     *
     * @param key
     * @param field
     * @param incrBy
     * @param suppressTran
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected Long hincrBy(final String key, final String field, Long incrBy, final boolean suppressTran) {
        Long result = getRedisTemplate(suppressTran).execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            Long value = connection.hIncrBy(byteKey, byteField, incrBy);
            if (value == null) {
                return 0L;
            }
            return value ;
        });
        return result;
    }

    /**
     *
     * @param key
     * @param field
     * @param incrBy
     * @return
     */
    protected Double hincrFloatBy(final String key, final String field, Double incrBy) {
        return hincrFloatBy(key, field, incrBy, false);
    }

    /**
     *
     * @param key
     * @param field
     * @param incrBy
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected Double hincrFloatBy(final String key, final String field, Double incrBy, final boolean suppressTran) {
        Double result = getRedisTemplate(suppressTran).execute((RedisCallback<Double>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            Double value = connection.hIncrBy(byteKey, byteField, incrBy);
            if (value == null) {
                return 0.0;
            }
            return value ;
        });
        return result;
    }


    @SuppressWarnings("rawtypes")
    protected Object hkeys(final String key) {
        Object result = getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Set setValue = connection.hKeys(byteKey);

            Iterator iter = setValue.iterator();
            List<Object> listObj = new ArrayList<Object>();
            while (iter.hasNext()) {
                Object obj = serializer.deserialize((byte[]) iter.next());
                listObj.add(obj);
            }
            return listObj;
        });
        return result;
    }

    protected Set<String> hkeysSerial(final String key) {
        Set<String> result = getRedisTemplate(true).execute((RedisCallback<Set<String>>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            Set<byte[]> setValue = connection.hKeys(byteKey);

            Iterator<byte[]> iter = setValue.iterator();
            Set<String> keys = new HashSet<>();
            while (iter.hasNext()) {
                String obj = serializer.deserialize(iter.next());
                keys.add(obj);
            }
            return keys;
        });
        return result;
    }

    /**
     * 从HashSet里面删除元素
     * @param key
     * @param field
     * @return
     */
    protected boolean hdel(final String key, final String field) {
        return hdel(key, field, false);
    }
    /**
     *  从HashSet里面删除元素
     * @param key
     * @param field
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean hdel(final String key, final String field, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteField = serializer.serialize(field);
            connection.hDel(byteKey, byteField);
            return true;
        });
        return result;
    }

    /**
     * 设置redis键过期时间
     *
     * @param key
     * @param seconds
     */
    protected boolean expire(final String key, final Long seconds) {
        return expire(key, seconds, false);
    }

    /**
     *
     * @param key
     * @param seconds
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected boolean expire(final String key, final Long seconds, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            connection.expire(byteKey, seconds);// 设置过期时间
            return true;
        });
        return result;
    }

    /**
     * @param keys
     */
    public void delete(List<K> keys) {
        delete(keys, false);
    }

    /**
     * @param keys
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     */
    public void delete(List<K> keys, final boolean suppressTran) {
        getRedisTemplate(suppressTran).delete(keys);
    }
    /**
     * 根据条件删除分页查询结果。
     */
    protected void deleteHashSetByPage(K hsetKey){
        List<K> listKeys = new ArrayList<K>(1) ;
        listKeys.add(hsetKey) ;
        delete(listKeys, true) ;
    }
    /**
     *
     * @param key
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     */
    public void delete(K key, final boolean suppressTran){
        getRedisTemplate(suppressTran).delete(key);
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在, 若给定的 key 已经存在，则 SETNX 不做任何动作。
     * @param key
     * @param value
     * @param seconds
     * @param suppressTran 是否压制事务，true，表示在立即执行到redis（不需要等事务提交，同时也不会因事务失败而回滚）
     * @return
     */
    protected Boolean setNx(final String key, final Serializable value, final long seconds, final boolean suppressTran) {
        boolean result = getRedisTemplate(suppressTran).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            Boolean result1 = connection.setNX(byteKey, byteValue);
            if (seconds > 0 && (null == result1 && !suppressTran) || (result1 && suppressTran)){
                connection.expire(byteKey, seconds);
            }
            return result1;
        });
        return result;
    }

    /**
     *
     * @return
     */
    public Long dbSize() {
        Long result = pojoRedisTemplate.execute((RedisCallback<Long>) connection -> connection.dbSize());
        return result;
    }

    public void clean(K pattern) {
        Set<K> keySet = stringRedisTemplate.keys(pattern);
        if (keySet != null && keySet.size() > 0) {
            List<K> keyList = new ArrayList<K>(keySet);
            delete(keyList, true);
        }
    }

    /**
     * pipeline方式获取数据
     *
     * @param keys
     * @return
     */
    protected List<Object> pipelineGet(final List<String> keys) {
        return getRedisTemplate(true).executePipelined((RedisCallback<Object>) connection -> {
            // 直接执行所有需要执行的命令，返回值由jedis通过底层connection.closePipeline()实现
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            for (String key : keys) {
                byte[] byteKey = serializer.serialize(key);
                connection.get(byteKey);
            }
            // jedis api限制 只能返回null 否则会抛异常
            return null;
        }, getRedisTemplate(true).getStringSerializer());

    }

    protected <T extends Serializable> List<T> pipelineGet(final Collection<String> keys, final Class<T> clazz) {
        List<?> tmps = getRedisTemplate(true).executePipelined((RedisCallback<Object>) connection -> {
            // 直接执行所有需要执行的命令，返回值由jedis通过底层connection.closePipeline()实现
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            for (String key : keys) {
                byte[] byteKey = serializer.serialize(key);
                connection.get(byteKey);
            }
            // jedis api限制 只能返回null 否则会抛异常
            return null;
        }, getPojoSerializer(clazz));
        List<T> result = new ArrayList<T>(tmps.size());
        for (Object obj : tmps) {
            result.add(clazz.cast(obj));
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////// List 操作接口//////////////////////////
    /////////////////////////////////////////////////////////////////
    /**
     * 使用redis自身事物，向一个定长的列表加入元素，每次都要传入list的长度。即外部可以不用放入事务中。
     * @param key
     * @param length
     * @param value
     * @param seconds
     * @return
     */
    protected boolean lpushWithTrim(final String key, final long length, final Serializable value, final Long seconds) {
        boolean result = getRedisTemplate(true).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            if (length > 0L) {
                connection.multi();
                connection.lPush(byteKey, byteValue) ;
                connection.lTrim(byteKey, 0, length-1);
                connection.exec() ;
            } else {
                connection.lPush(byteKey, byteValue);
            }
            if (seconds.equals(0L)) {
                connection.persist(byteKey);
            } else {
                connection.expire(byteKey, seconds);// 设置过期时间
            }
            return true;
        });
        return result;
    }

    /**
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    protected boolean lpush(final String key, final Serializable value, final Long seconds) {
        boolean result = pojoRedisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteValue = CommonUtil.transObj2ByteArray(value);
            connection.lPush(byteKey, byteValue);
            if (seconds.equals(0L)) {
                connection.persist(byteKey);
            } else {
                connection.expire(byteKey, seconds);// 设置过期时间
            }
            return true;
        });
        return result;
    }

    /**
     * @param key
     * @param begin
     * @param end
     * @return
     */
    protected boolean ltrim(final String key, long begin, long end) {
        boolean result = pojoRedisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            connection.multi();
            connection.lTrim(byteKey, begin, end);
            connection.exec();
            return true;
        });
        return result;
    }

    /**
     * @param key
     * @param begin
     * @param end
     * @return
     */
    protected <T extends Serializable> List<T> lrange(final String key, long begin, long end, final Class<T> clazz) {
        List<T> result = getRedisTemplate(true).execute((RedisCallback<List<T>>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            List<byte[]> listByteValues = connection.lRange(byteKey, begin, end);
            List<T> listValue = new ArrayList<T>(listByteValues.size());
            for (byte[] value : listByteValues) {
                listValue.add(CommonUtil.transByteArray2Obj(value, clazz));
            }
            return listValue;
        });
        return result;
    }

    /////////////////////////////////////////////////////////////
    /////////////////////// Pipeline 方式 ///////////////////////
    /////////////////////////////////////////////////////////////
    // 用pipeline的方式实现get多个数据
    protected List<String> pget(final List<String> keys) {

        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) getRedisTemplate(true).execute((RedisCallback<Object>) connection -> {
            connection.openPipeline();

            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            for (String key : keys) {
                byte[] byteKey = serializer.serialize(key);
                connection.get(byteKey);
            }
            List<?> pipeResult = connection.closePipeline();
            List<String> strObjs = new ArrayList<>();
            for (Object tmp : pipeResult) {
                byte[] bytes = (byte[]) tmp;
                String tmpObj = serializer.deserialize(bytes);
                strObjs.add(tmpObj);
            }
            return strObjs;
        });

        return result;
    }

    /**
     * 判断 value 元素是否集合(set) key 的成员
     *
     * @param key
     * @param value
     * @return
     */
    protected boolean sIsMember(final String key, final String value) {
        boolean result = getRedisTemplate(true).execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisTemplate(true).getStringSerializer();
            byte[] byteKey = serializer.serialize(key);
            byte[] byteVal = serializer.serialize(value);
            return connection.sIsMember(byteKey, byteVal);
        });
        return result;
    }

    /**
     * 是否抑制事务,在事务里面的 Redis写操作，可能会出现很多不一样的后果，调用者要仔细。
     * @param suppTrans
     * @return
     */
    private RedisTemplate<K, V> getRedisTemplate(Boolean suppTrans){
        stringRedisTemplate.setEnableTransactionSupport(false);
        pojoRedisTemplate.setEnableTransactionSupport(true);
        return suppTrans ? stringRedisTemplate : pojoRedisTemplate ;
    }

    protected <T extends Serializable> RedisSerializer<T> getPojoSerializer(Class<T> clazz) {
        return new PojoSerializable<T>(clazz);
    }

    protected RedisSerializer<String> getRedisSerializer() {
        return getRedisTemplate(true).getStringSerializer();
    }


    public void closeConn() {
        RedisConnection conn = RedisConnectionUtils.getConnection(pojoRedisTemplate.getConnectionFactory());
        conn.close();
    }
}
