package com.cloud.ftl.ftlbasic.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.vavr.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 反射工具类
 *
 * @author lijun
 * @since 1.0.0
 */
@Slf4j
public class ReflectUtil {

    //------------------------------------- Field ------------------------------------------------

    public static boolean hasField(Class<?> beanClass, String fieldName) {
        return null != getField(beanClass, fieldName,true);
    }

    public static List<Field> getFields(Class<?> clazz, Boolean ingSup,Boolean useCache) {
        try{
            if(useCache){
                return CacheUtil.allFieldCache.get(Pair.of(clazz,ingSup));
            } else {
                return allField(clazz,ingSup);
            }
        } catch (Exception e){
            log.error("获取【{}】的所有域失败:",clazz.getSimpleName(),e);
        }
        return Lists.newArrayList();
    }

    public static List<Field> allField(Class<?> clazz,Boolean ingSup) {
        Set<Field> fieldSet = Sets.newLinkedHashSet();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                field.setAccessible(true);
                fieldSet.add(field);
            }
        }

        if(!ingSup){
            superField(clazz, fieldSet);
        }
        return Lists.newArrayList(fieldSet);
    }

    private static void superField(Class<?> clazz, Set<Field> fieldSet) {
        Class<?> superClass = clazz.getSuperclass();
        if(Objects.isNull(superClass)){
            return;
        }
        Field[] superFields = superClass.getDeclaredFields();
        if (superFields != null && superFields.length > 0) {
            for (Field field : superFields) {
                field.setAccessible(true);
                fieldSet.add(field);
            }
            superField(superClass, fieldSet);
        }
    }

    public static Field getField(Class<?> clazz,String fieldName, Boolean useCache){
        try {
            if(useCache){
                return CacheUtil.fieldCache.get(Pair.of(clazz,fieldName));
            } else {
                return clazz.getDeclaredField(fieldName);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】field域【{}】失败",clazz,fieldName);
        }
        return null;
    }

    public static Field getFieldContainSuper(Class<?> clazz,String fieldName, Boolean useCache){
        try {
            if(useCache){
                return CacheUtil.fieldCache.get(Pair.of(clazz,fieldName));
            } else {
                Map<String, Field> fieldMap = allField(clazz, false)
                        .stream().collect(Collectors.toMap(Field::getName, Function.identity(), (oldValue,newValue)->oldValue));
                return fieldMap.getOrDefault(fieldName,null);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】field域【{}】失败",clazz,fieldName);
        }
        return null;
    }

    public static <T> Object getFieldValue(T t,String fieldName, Boolean useCache){
        try {
            Field field = getField(t.getClass(), fieldName, useCache);
            if(Objects.nonNull(field)){
                return field.get(t);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】field域【{}】失败",t.getClass().getSimpleName(),fieldName);
        }
        return null;
    }

    public static <T> Object getFieldValueContainSuper(T t,String fieldName, Boolean useCache){
        try {
            Field field = getFieldContainSuper(t.getClass(), fieldName, useCache);
            if(Objects.nonNull(field)){
                return field.get(t);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】field域【{}】失败",t.getClass().getSimpleName(),fieldName);
        }
        return null;
    }

    public static <T> void setFieldValue(T t, String fieldName, Object object,Boolean useCache) {
        try {
            Field field = getField(t.getClass(), fieldName,useCache);
            if(Objects.nonNull(field)){
                field.setAccessible(true);
                field.set(t,object);
            }
        } catch (Exception e) {
            log.error("设置实体【{}】,【{}】域失败",t.getClass().getName(),fieldName);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> void setFieldValueContainSuper(T t, String fieldName, Object object,Boolean useCache) {
        try {
            Field field = getFieldContainSuper(t.getClass(), fieldName,useCache);
            if(Objects.nonNull(field)){
                field.setAccessible(true);
                field.set(t,object);
            }
        } catch (Exception e) {
            log.error("设置实体【{}】,【{}】域失败",t.getClass().getName(),fieldName);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Map<Field,Field> getEqualFields(Class<?> sClass,Class<?> tClass,Boolean ingSup,Boolean useCache) {
        Map<Field, Field> equalFieldMap = Maps.newHashMap();
        try{
            if(useCache){
                equalFieldMap = CacheUtil.equalFieldsCache.get(Tuple.of(sClass, tClass,ingSup));
            } else {
                List<Field> sFieldList = getFields(sClass,ingSup,false);
                List<Field> tFieldList = getFields(tClass,ingSup,false);
                Map<String, Field> sFieldMap = sFieldList.stream()
                        .collect(Collectors.toMap(Field::getName, Function.identity()));
                Map<String, Field> tFieldMap = tFieldList.stream()
                        .collect(Collectors.toMap(Field::getName, Function.identity()));
                //保留相同的field
                for (Map.Entry<String, Field> entry : tFieldMap.entrySet()) {
                    String tFieldName = entry.getKey();
                    Field tField = entry.getValue();
                    Field sField = sFieldMap.getOrDefault(tFieldName, null);
                    if(Objects.isNull(sField) || Modifier.isFinal(sField.getModifiers())
                            || Modifier.isFinal(tField.getModifiers())
                            || sField.getModifiers() != tField.getModifiers()
                            || !sField.getType().equals(tField.getType())){
                        continue;
                    }
                    equalFieldMap.put(sField,tField);
                }
            }
        } catch (Exception e) {
            log.error("获取实体【{}】和实体【{}】相同的域失败",
                    sClass.getClass().getName(),tClass.getClass().getName());
        }
        return equalFieldMap;
    }

    //------------------------------------- Method --------------------------------------------------------------------------

    public static List<Method> getMethods(Class<?> clazz, Boolean ingSup,Boolean useCache) {
        try{
            if(useCache){
                return CacheUtil.allMethodCache.get(Pair.of(clazz,ingSup));
            } else {
                return allMethod(clazz,ingSup);
            }
        } catch (Exception e){
            log.error("获取【{}】的所有方法失败:",clazz.getSimpleName(),e);
        }
        return null;
    }

    public static List<Method> allMethod(Class<?> beanClass, boolean ingSup) {
        Set<Method> methodSet = Sets.newLinkedHashSet();
        Method[] methods = beanClass.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                method.setAccessible(true);
                methodSet.add(method);
            }
        }

        if(!ingSup){
            superMethod(beanClass, methodSet);
        }
        return Lists.newArrayList(methodSet);
    }

    private static void superMethod(Class<?> beanClass, Set<Method> methodSet) {
        Class<?> superClass = beanClass.getSuperclass();
        if(Objects.isNull(superClass)){
            return;
        }
        Method[] methods = superClass.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                method.setAccessible(true);
                methodSet.add(method);
            }
            superMethod(superClass, methodSet);
        }
    }

    public static Method getMethod(Boolean useCache, Class<?> clazz, String methodName, Class<?>... parameterTypes){
        try {
            if(useCache){
                Class<?>[] parameterArr = Arrays.stream(parameterTypes).toArray(Class<?>[]::new);
                return CacheUtil.methodCache.get(Tuple.of(clazz,methodName,parameterArr));
            } else {
                return clazz.getDeclaredMethod(methodName,parameterTypes);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】Method方法【{}】失败",clazz,methodName);
        }
        return null;
    }

    public static Method getMethodContainSuper(Boolean useCache, Class<?> clazz, String methodName, Class<?>... parameterTypes){
        try {
            if(useCache){
                Class<?>[] parameterArr = Arrays.stream(parameterTypes).toArray(Class<?>[]::new);
                return CacheUtil.methodCache.get(Tuple.of(clazz,methodName,parameterArr));
            } else {
                Map<Pair<String, Class<?>[]>, Method> methodMap = allMethod(clazz, false)
                        .stream().collect(Collectors.toMap(e -> Pair.of(e.getName(), e.getParameterTypes()), Function.identity(),
                                (oldValue, newValue) -> oldValue));
                return methodMap.getOrDefault(Pair.of(methodName,parameterTypes),null);
            }
        } catch (Exception e) {
            log.error("获取实体【{}】Method方法【{}】失败",clazz,methodName);
        }
        return null;
    }

    public static <T> Object invokeMethod(Boolean useCache,T t, String methodName,Object... parameterValues){
        try {
            Class<?>[] parameterTypes = Arrays.stream(parameterValues).map(Object::getClass).toArray(Class<?>[]::new);
            Method method = getMethod(useCache, t.getClass(), methodName, parameterTypes);
            if(Objects.nonNull(method)){
                return method.invoke(t, parameterValues);
            } else {
                throw new RuntimeException("执行函数不存在");
            }
        } catch (Exception e) {
            log.error("执行实体【{}】Method方法【{}】失败",t.getClass().getName(),methodName);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> Object invokeMethodContainSuper(Boolean useCache,T t, String methodName,Object... parameterValues){
        try {
            Class<?>[] parameterTypes = Arrays.stream(parameterValues).map(Object::getClass).toArray(Class<?>[]::new);
            Method method = getMethodContainSuper(useCache, t.getClass(), methodName, parameterTypes);
            if(Objects.nonNull(method)){
                return method.invoke(t,parameterValues);
            } else {
                throw new RuntimeException("执行函数不存在");
            }
        } catch (Exception e) {
            log.error("执行实体【{}】Method方法【{}】失败",t.getClass().getName(),methodName);
            throw new RuntimeException(e.getMessage());
        }
    }
}
