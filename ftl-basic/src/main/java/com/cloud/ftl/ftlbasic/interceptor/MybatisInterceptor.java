package com.cloud.ftl.ftlbasic.interceptor;
import org.apache.ibatis.plugin.*;
import java.util.Properties;

//@Intercepts({
//        @Signature(
//                type= Executor.class,
//                method = "query",
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
//        ),
//        @Signature(
//                type= Executor.class,
//                method = "update",
//                args = {MappedStatement.class, Object.class}
//        )
//})
//@Component
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        //拦截器可以在这里做增强
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
