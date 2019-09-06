package com.cloud.ftl.ftlbasic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class PatternDateAspect {

    @Pointcut("@annotation(com.cloud.ftl.ftlbasic.annotation.PatternDate)")
    public void annotationPoinCut(){}

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach((Object e) -> {
            if(e instanceof Date && e.getClass().getSimpleName().startsWith("start")){
                log.info("------- format startDate --------");
                e = getDayStartDate((Date)e);
            }
            if(e instanceof Date && e.getClass().getSimpleName().startsWith("end")){
                log.info("------- format endDate --------");
                e = getDayEndDate((Date)e);
            }
        });
    }

     private Date getDayStartDate(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.add(Calendar.DATE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.MILLISECOND, 0);
            return ca.getTime();
        }
    }

    private static Date getDayEndDate(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.HOUR_OF_DAY, 23);
            ca.add(Calendar.DATE, 0);
            ca.set(Calendar.SECOND, 59);
            ca.set(Calendar.MINUTE, 59);
            return ca.getTime();
        }
    }
}
