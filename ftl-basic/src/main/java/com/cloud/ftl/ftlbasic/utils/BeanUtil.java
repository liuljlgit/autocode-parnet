package com.cloud.ftl.ftlbasic.utils;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class BeanUtil {

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 创建新实体，并且把传入实体中的值拷贝到新实体的相同属性
     * @param source 数据源
     * @param clazz 目标实体的类型
     * @return
     * @throws Exception
     */
    public static <T> T createBean(Object source,Class<T> clazz){
        T t;
        try {
            t = clazz.newInstance();
            if(Objects.isNull(source)) {
                return t;
            }
            copyOwnerAndSuperProperties(source, t);
        } catch (Exception e) {
            logger.error("实体 {} 转换为 {} 类型失败：{}",source,clazz,e);
            throw new BusiException("实体类型转换失败");
        }
        return t;
    }

    /**
     * 将source对象属性的值赋给target对象同属性名的属性
     * @param source 值的对象
     * @param target 接收值的对象
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T,B> T copyOwnerAndSuperProperties(B source,T target) throws Exception {
        List<Field> voFileds = RegxUtil.allField(source.getClass());
        List<Field> modelFileds = RegxUtil.allField(target.getClass());
        conpyField((B) source, (T) target, voFileds, modelFileds);
        return target;
    }

    /**
     * 复制属性
     * @param source
     * @param target
     * @param voFileds
     * @param modelFileds
     * @param <T>
     * @param <B>
     * @throws IllegalAccessException
     */
    public static <T, B> void conpyField(B source, T target, List<Field> voFileds, List<Field> modelFileds) throws Exception {
        for (Field voFiled : voFileds) {
            if (Modifier.isFinal(voFiled.getModifiers()))
                continue;
            voFiled.setAccessible(true);
            for (Field modelField : modelFileds) {
                if (Modifier.isFinal(modelField.getModifiers()))
                    continue;
                modelField.setAccessible(true);
                if (modelField.getName().equals(voFiled.getName())) {
                    boolean isSwap = modelField.getType().isPrimitive() || voFiled.getType().isPrimitive();
                    if (!isSwap) { // 如果存在基本类型，不判断类型
                        if (modelField.getType() != voFiled.getType()) {
                            continue;
                        }
                    }

                    Object voValue = voFiled.get(source);
                    if (voValue != null){
                        Method method = target.getClass().getMethod("set"+toUpperCaseFirstOne(modelField.getName()),voFiled.getType());
                        method.invoke(target,voValue);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0))){
            return s;
        }else{
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

}
