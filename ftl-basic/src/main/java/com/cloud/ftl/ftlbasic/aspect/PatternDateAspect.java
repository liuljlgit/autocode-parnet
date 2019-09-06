package com.cloud.ftl.ftlbasic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class PatternDateAspect {

    @Pointcut("@annotation(com.cloud.ftl.ftlbasic.annotation.PatternDate)")
    public void annotationPoinCut(){}

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(e -> {
            if(e.getClass().getSimpleName().startsWith("start") && e instanceof Date){
                log.info("------- format startDate --------");
            }
            if(e.getClass().getSimpleName().startsWith("end") && e instanceof Date){
                log.info("------- format endDate --------");
            }
        });
    }
}
