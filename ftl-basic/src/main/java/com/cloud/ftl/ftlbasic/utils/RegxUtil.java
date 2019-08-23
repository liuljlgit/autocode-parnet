package com.cloud.ftl.ftlbasic.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
public class RegxUtil {

    /**
     * 获取对象的所有Field，包含所有超类
     * @param clazz
     * @return
     */
    public static List<Field> allField(Class<?> clazz){
        Set<Field> rsList = new LinkedHashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        if(fields!=null&&fields.length>0){
            for(Field f:fields){
                rsList.add(f);
            }
        }
        superField(clazz,rsList);

        List<Field> rs = new LinkedList<>();
        rs.addAll(rsList);
        return rs;
    }

    /**
     * 获取所有超类
     * @param clazz
     * @param rsList
     */
    private static void superField(Class<?> clazz,Set<Field> rsList){
        Set<Field> tempSet = new LinkedHashSet<>();
        Class<?> superClass = clazz.getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        if(superFields!=null&&superFields.length>0){
            for(Field f:superFields){
                tempSet.add(f);
            }
            tempSet.addAll(rsList);
            rsList.removeAll(rsList);
            rsList.addAll(tempSet);
            superField(superClass,rsList);
        }
    }
}
