package com.cloud.ftl.ftlbasic.utils;

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

    /**
     * 首字母转小写
     *
     * @param str 需转换的字符
     * @return
     */
    public static String toLowerCaseFirstOne(String str){
        if(Character.isLowerCase(str.charAt(0))){
            return str;
        }else{
            return (new StringBuilder())
                    .append(Character.toLowerCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param str 需转换的字符
     * @return
     */
    public static String toUpperCaseFirstOne(String str){
        if(Character.isUpperCase(str.charAt(0))){
            return str;
        }else{
            return (new StringBuilder())
                    .append(Character.toUpperCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
        }
    }

}
