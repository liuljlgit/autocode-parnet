package com.cloud.ftl.ftlbasic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Aspect
@Slf4j
@Component
public class PatternDateAspect {

    @Pointcut("@annotation(com.cloud.ftl.ftlbasic.annotation.PatternDate)")
    public void annotationPoinCut(){}

    @Around("annotationPoinCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] argNames = methodSignature.getParameterNames();    //参数名
        Object[] args = joinPoint.getArgs();                        //参数值
        for(int i=0;i < argNames.length;i++){
            String argName = argNames[i];
            Object argVal = args[i];
            if(argVal instanceof Date){
                if(argName.startsWith("start")){
                    argVal = getDayStartDate((Date)argVal);
                }
                if(argName.startsWith("end")){
                    argVal = getDayEndDate((Date)argVal);
                }
                args[i] = argVal;
            }
            if(!argVal.getClass().getTypeName().startsWith("java.util")
                    && !argVal.getClass().getTypeName().startsWith("java.lang")){
                patternField(argVal);
            }
        }
        return joinPoint.proceed(args);
    }

    private void patternField(Object arg) {
        Arrays.stream(arg.getClass().getDeclaredFields())
                //.filter(field -> Objects.nonNull(field.getAnnotation(PatternDate.class)))
                .filter(field -> field.getGenericType().getTypeName().equals("java.util.Date"))
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    try {
                        Object obj = field.get(arg);
                        if (Objects.isNull(obj)) {
                            return;
                        }
                        String fieldName = field.getName();
                        if(fieldName.startsWith("start")){
                            field.set(arg, getDayStartDate((Date)obj));
                        } else if(fieldName.startsWith("end")) {
                            field.set(arg, getDayEndDate((Date)obj));
                        }
                    } catch (Exception e) {
                        log.error("反射注入时间报错", e);
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
