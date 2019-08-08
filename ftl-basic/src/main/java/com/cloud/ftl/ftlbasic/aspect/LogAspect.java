package com.cloud.ftl.ftlbasic.aspect;

import com.cloud.ftl.ftlbasic.utils.RegxUtil;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.cloud.ftl.ftlbasic.aspect.PropInject)")
    public void annotationPoinCut(){}

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) throws Exception {
        System.out.println("-------sdsad");
        Object[] args = joinPoint.getArgs();
        if(args.length != 1){
            return;
        }
        Object target = args[0];
        Class<?> tClass = target.getClass();
        if(tClass.getSuperclass() != BaseQuery.class){
            return;
        }
        Field[] fields = tClass.getDeclaredFields();
        Method acMethod = tClass.getSuperclass().getDeclaredMethod("andCriteria", null);
    }
}
