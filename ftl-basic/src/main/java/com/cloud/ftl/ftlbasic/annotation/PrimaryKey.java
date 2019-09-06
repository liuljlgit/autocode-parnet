package com.cloud.ftl.ftlbasic.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryKey {

}
