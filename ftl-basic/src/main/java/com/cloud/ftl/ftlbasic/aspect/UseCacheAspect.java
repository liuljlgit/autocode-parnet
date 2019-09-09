package com.cloud.ftl.ftlbasic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class UseCacheAspect {

    @Pointcut("@within(com.cloud.ftl.ftlbasic.annotation.UseCache)")
    public void annotationPoinCut(){}

    @Around("annotationPoinCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] argNames = methodSignature.getParameterNames();    //参数名
        Object[] argValues = joinPoint.getArgs();                   //参数值
        String methodName = signature.getName();                    //方法名
        
        return joinPoint.proceed(argValues);
    }


}
