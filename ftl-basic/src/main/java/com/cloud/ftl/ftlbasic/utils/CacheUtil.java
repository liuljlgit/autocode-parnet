package com.cloud.ftl.ftlbasic.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.vavr.Tuple3;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * guava缓存工具类
 *
 * @author lijun
 * @since 1.0.0
 */
public class CacheUtil {

    private static final Long FC_DURATION = 1L;

    private static final TimeUnit FC_TIMEUNIT = TimeUnit.HOURS;

    /**
     * field comment:
     * 缓存某个类下的所有Field
     */
    static LoadingCache<Pair<Class<?>,Boolean>,List<Field>> allFieldCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(FC_DURATION, FC_TIMEUNIT)
            .build(
                    new CacheLoader<Pair<Class<?>,Boolean>,List<Field>>(){
                        @Override
                        public List<Field> load(Pair<Class<?>,Boolean> key) {
                            return ReflectUtil.allField(key.getLeft(),key.getRight());
                        }
                    }
            );

    /**
     * field comment:
     * 获取某个类的Field
     */
    static LoadingCache<Tuple3<Class<?>,String,Boolean>,Field> fieldCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(FC_DURATION, FC_TIMEUNIT)
            .build(
                    new CacheLoader<Tuple3<Class<?>,String,Boolean>,Field>(){
                        @Override
                        public Field load(Tuple3<Class<?>,String,Boolean> key) {
                            Class<?> classz = key._1;
                            String fieldName = key._2;
                            Boolean isContainSuper = key._3;
                            if(isContainSuper){
                                return ReflectUtil.getFieldContainSuper(classz,fieldName,false);
                            } else {
                                return ReflectUtil.getField(classz,fieldName,false);
                            }
                        }
                    }
            );

    /**
     * 获取具有相同属性的Field
     * key._1：source class
     * key._2：target class
     * key._3：是否包含父类
     */
    static LoadingCache<Tuple3<Class<?>,Class<?>,Boolean>,Map<Field,Field>> equalFieldsCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(FC_DURATION, FC_TIMEUNIT)
            .build(
                    new CacheLoader<Tuple3<Class<?>,Class<?>,Boolean>,Map<Field,Field>>(){
                        @Override
                        public Map<Field,Field> load(Tuple3<Class<?>,Class<?>,Boolean> key) {
                            return ReflectUtil.getEqualFields(key._1,key._2,key._3,false);
                        }
                    }
            );

    /**
     * 获取某个类的Method
     * key.getLeft()：target class
     * key.getRight()：是否包含父类
     */
    static LoadingCache<Pair<Class<?>,Boolean>,List<Method>> allMethodCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(FC_DURATION, FC_TIMEUNIT)
            .build(
                    new CacheLoader<Pair<Class<?>,Boolean>,List<Method>>(){
                        @Override
                        public List<Method> load(Pair<Class<?>,Boolean> key) {
                            return ReflectUtil.allMethod(key.getLeft(),key.getRight());
                        }
                    }
            );

}
