package com.cloud.ftl.ftlbasic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("@annotation(com.cloud.ftl.ftlbasic.aspect.PropInject)")
    public void annotationPoinCut(){}

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) {
        log.info("-------- LogAspect Aspect ---------");
    }
}
