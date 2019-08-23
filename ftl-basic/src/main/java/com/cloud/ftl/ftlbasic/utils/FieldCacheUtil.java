package com.cloud.ftl.ftlbasic.utils;

import com.cloud.ftl.ftlbasic.webEntity.BasePage;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 存储反射缓存
 * @author lijun
 */
@Slf4j
public class FieldCacheUtil {

    public static Method getPMet;

    public static Method getPSMet;

    public static Method setPMet;

    public static Method setPSMet;

    public static Map<Class<?>,Field> priKeyCache;

    static {
        try {
            Class<BasePage> bClass = BasePage.class;
            getPMet = bClass.getDeclaredMethod("getPage");
            getPSMet = bClass.getDeclaredMethod("getPageSize");
            setPMet = bClass.getDeclaredMethod("setPage",Integer.class);
            setPSMet = bClass.getDeclaredMethod("setPageSize",Integer.class);
            priKeyCache = Maps.newConcurrentMap();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
