package com.cloud.ftl.ftlbasic.utils;

/**
 * 把字符串转换成驼峰式命名法
 * @author lijun
 */
public class HumpUtil {

    /**
     * 转换成驼峰式命名法
     *
     * @param fieldName 字段名
     * @return
     */
    public static String convertToJava(String fieldName) {
        String[] words = fieldName.split("_");
        return toUppercase4FirstLetter(words);
    }

    /**
     * 转换成Java类命名
     *
     * @param fieldName 字段名
     */
    public static String convertToJavaClass(String fieldName) {
        return toUpperCaseFirstOne(convertToJava(fieldName));
    }

    /**
     * 转换成驼峰式命名法
     * @param words
     * @return
     */
    private static String toUppercase4FirstLetter(String... words){
        StringBuilder buffer = new StringBuilder("");
        if(words != null && words.length > 0){
            for(int i = 0;i < words.length;i++){
                String word = words[i];
                String firstLetter = word.substring(0, 1);
                String others = word.substring(1);
                String upperLetter = i != 0 ? firstLetter.toUpperCase() : firstLetter;
                buffer.append(upperLetter).append(others);
            }
        }
        return buffer.toString();
    }

    /**
     * 首字母转小写
     * @param str
     * @return
     */
    private static String toLowerCaseFirstOne(String str){
        return Character.isLowerCase(str.charAt(0)) ?
                str : (new StringBuilder())
                        .append(Character.toLowerCase(str.charAt(0)))
                        .append(str.substring(1))
                        .toString();
    }

    /**
     * 首字母转大写
     * @param str
     * @return
     */
    private static String toUpperCaseFirstOne(String str){
        return Character.isUpperCase(str.charAt(0)) ?
                str : (new StringBuilder())
                        .append(Character.toUpperCase(str.charAt(0)))
                        .append(str.substring(1))
                        .toString();
    }

}
