package com.cloud.ftl.ftlautocode.util;

import com.google.common.base.CaseFormat;

/**
 * 把字符串转换成驼峰式命名法
 * @author lijun
 */
public class HumpUtil {

    /**
     * 转换成驼峰式命名法
     *
     * @param fieldName 字段名
     */
    public static String convertToJava(String fieldName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);
    }

    /**
     * 转换成Java类命名
     *
     * @param fieldName 字段名
     */
    public static String convertToJavaClass(String fieldName) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fieldName);
    }

}
