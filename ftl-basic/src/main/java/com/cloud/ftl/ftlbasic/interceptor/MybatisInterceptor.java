package com.cloud.ftl.ftlbasic.interceptor;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Intercepts({
        @Signature(
                type= Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type= Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
@Component
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object target = args[1];
        Class<?> tClass = target.getClass();
        Field mapField = tClass.getDeclaredField("map");
        Map<String,String> map = (Map<String,String>)mapField.get(target);
        if(tClass.getSuperclass() != null && tClass.getSuperclass() == BaseQuery.class){
            Class<?> sClass = tClass.getSuperclass();
            Method acMethod = sClass.getDeclaredMethod("addCriteria", String.class,Object.class);
            Field[] tFields = tClass.getDeclaredFields();
            for (Field tField : tFields) {
                String tName = tField.getName();
                if("map".equals(tName)){
                    continue;
                }
                tField.setAccessible(true);
                Object obj = tField.get(target);
                if(Objects.nonNull(obj)){
                   acMethod.invoke(target, map.get(tName),obj);
                }
            }

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
