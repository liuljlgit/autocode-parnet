package com.cloud.ftl.ftlbasic.utils;

import com.cloud.ftl.ftlbasic.func.InitSupplier;
import com.cloud.ftl.ftlbasic.func.OptSupplier;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.Objects;

/**
 * 对map一些方法的增强
 * @author lijun
 */
@Slf4j
public class MapUtil {

    /**
     * 从map中获取值，如果没有则设定一个默认值
     * @param map
     * @param key
     * @param initSupplier
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> V getOrPutDefault(Map<K,V> map, K key, InitSupplier<V> initSupplier) {
        try {
            V defaultVal = map.getOrDefault(key,null);
            if(Objects.isNull(defaultVal)){
                V call = initSupplier.call();
                map.putIfAbsent(key,call);
                return call;
            }
            return defaultVal;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 设置默认值，设置操作，返回新值
     * @param map 需要操作的map
     * @param key 需要操作的key
     * @param optSupplier 设置操作回调方法
     * @param <K> key的类型
     * @param <V> value的类型
     * @return 操作之后返回的新值
     */
    public static <K,V> V getHandleSetVal(Map<K,V> map,K key, OptSupplier<V> optSupplier){
        try {
            V oldVal;
            oldVal = map.getOrDefault(key,null);
            if(Objects.isNull(oldVal)){
                return null;
            }
            V call = optSupplier.call(oldVal);
            map.put(key,call);
            return oldVal;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 设置默认值，设置操作，返回新值
     * @param map 需要操作的map
     * @param key 需要操作的key
     * @param initSupplier 设置默认值回调方法
     * @param optSupplier 设置操作回调方法
     * @param <K> key的类型
     * @param <V> value的类型
     * @return 操作之后返回的新值
     */
    public static <K,V> V getHandleSetVal(Map<K,V> map, K key, InitSupplier<V> initSupplier, OptSupplier<V> optSupplier){
        try {
            V oldVal;
            oldVal = map.getOrDefault(key,null);
            if(Objects.isNull(oldVal)){
                oldVal = getOrPutDefault(map, key, initSupplier);
            }
            V call = optSupplier.call(oldVal);
            map.put(key,call);
            return oldVal;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
