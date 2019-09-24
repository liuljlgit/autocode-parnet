package com.cloud.ftl.ftlbasic.constant;

/**
 * 常用的日期转换模式
 * @author Liulj
 * @version v 0.1 2019/9/23 14:24
 */
public interface DatePatternConst {

    String NORM_DATE_PATTERN = "yyyy-MM-dd";

    String PURE_DATE_PATTERN = "yyyyMMdd";

    String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

    String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";

    String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";

    String EN_DATE_PATTERN = "yyyy/MM/dd";

    String EN_DATE_MONTH_PATTERN = "yyyy/MM";

    String EN_DATETIME_MINUTE_PATTERN = "yyyy/MM/dd HH:mm";

    String EN_DATETIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    String CN_DATE_PATTERN = "yyyy年MM月dd日";

    String CN_DATE_MONTH_PATTERN = "yyyy年MM月";

    String CN_DATETIME_MINUTE_PATTERN = "yyyy年MM月dd日 HH:mm";

    String CN_DATETIME_PATTERN = "yyyy年MM月dd日 HH:mm:ss";

    String SIMPLE_DATE_MONTH_PATTERN = "yyyy-MM";

    String SIMPLE_DATE_DAY_PATTERN = "MM-dd";

    String SIMPLE_DATETIME_MINUTE_PATTERN = "MM-dd HH:mm";

    String SIMPLE_DATETIME_SECOND_PATTERN = "MM-dd HH:mm:ss";

    String SIMPLE_TIME_MINUTE_PATTERN = "HH:mm";

    String NORM_TIME_PATTERN = "HH:mm:ss";

    String PURE_TIME_PATTERN = "HHmmss";

    String EN_SIMPLE_DATE_DAY_PATTERN = "MM/dd";

    String EN_SIMPLE_DATETIME_MINUTE_PATTERN = "MM/dd HH:mm";

    String EN_SIMPLE_DATETIME_SECOND_PATTERN = "MM/dd HH:mm:ss";

    String CN_SIMPLE_DATE_DAY_PATTERN = "MM月dd日";

    String CN_SIMPLE_DATETIME_MINUTE_PATTERN = "MM月dd日 HH:mm";

    String CN_SIMPLE_DATETIME_SECOND_PATTERN = "MM月dd日 HH:mm:ss";

    String SPECIAL_SIMPLE_DATE_PATTERN = "yyyy.MM.dd";

    String SPECIAL_SIMPLE_DATE_DAY_PATTERN = "MM.dd";

    String AD_DATETIME_PATTERN = "G y-MM-dd Z E HH:mm:ss:SSS a";

    String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    String JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
}
