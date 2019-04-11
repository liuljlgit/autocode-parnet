package com.cloud.ftl.ftlbasic.redis;

import com.cloud.ftl.ftlbasic.utils.SerializableUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;

/**
 * 自定义redis序列化类2
 *
 * @param <T>
 */

public class PojoSerializable<T extends Serializable> implements RedisSerializer<T> {

    private Class<T> clazz;

    public PojoSerializable(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return SerializableUtil.transObj2ByteArray(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return SerializableUtil.transByteArray2Obj(bytes, clazz);
    }

}
