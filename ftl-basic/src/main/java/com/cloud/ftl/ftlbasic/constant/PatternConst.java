package com.cloud.ftl.ftlbasic.constant;

/**
 * 常用的日期转换模式
 * @author Liulj
 * @version v 0.1 2019/9/23 14:24
 */
public interface PatternConst {

    String NORM_DATE = "yyyy-MM-dd";

    String PURE_DATE = "yyyyMMdd";

    String NORM_DATETIME = "yyyy-MM-dd HH:mm:ss";

    String NORM_DATETIME_MINUTE = "yyyy-MM-dd HH:mm";

    String PURE_DATETIME = "yyyyMMddHHmmss";

    String PURE_DATETIME_MS = "yyyyMMddHHmmssSSS";

    String EN_DATE = "yyyy/MM/dd";

    String EN_DATE_MONTH = "yyyy/MM";

    String EN_DATETIME_MINUTE = "yyyy/MM/dd HH:mm";

    String EN_DATETIME = "yyyy/MM/dd HH:mm:ss";

    String CN_DATE = "yyyy年MM月dd日";

    String CN_DATE_MONTH = "yyyy年MM月";

    String CN_DATETIME_MINUTE = "yyyy年MM月dd日 HH:mm";

    String CN_DATETIME = "yyyy年MM月dd日 HH:mm:ss";

    String SIMPLE_DATE_MONTH = "yyyy-MM";

    String SIMPLE_DATE_DAY = "MM-dd";

    String SIMPLE_DATETIME_MINUTE = "MM-dd HH:mm";

    String SIMPLE_DATETIME_SECOND = "MM-dd HH:mm:ss";

    String SIMPLE_TIME_MINUTE = "HH:mm";

    String NORM_TIME = "HH:mm:ss";

    String PURE_TIME = "HHmmss";

    String EN_SIMPLE_DATE_DAY = "MM/dd";

    String EN_SIMPLE_DATETIME_MINUTE = "MM/dd HH:mm";

    String EN_SIMPLE_DATETIME_SECOND = "MM/dd HH:mm:ss";

    String CN_SIMPLE_DATE_DAY = "MM月dd日";

    String CN_SIMPLE_DATETIME_MINUTE = "MM月dd日 HH:mm";

    String CN_SIMPLE_DATETIME_SECOND = "MM月dd日 HH:mm:ss";

    String SPECIAL_SIMPLE_DATE = "yyyy.MM.dd";

    String SPECIAL_SIMPLE_DATE_DAY = "MM.dd";

    String AD_DATETIME = "G y-MM-dd Z E HH:mm:ss:SSS a";

    String HTTP_DATETIME = "EEE, dd MMM yyyy HH:mm:ss z";

    String JDK_DATETIME = "EEE MMM dd HH:mm:ss zzz yyyy";

    String UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    String TIMEZONE = "GMT+8";
}
