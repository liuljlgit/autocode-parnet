package com.cloud.ftl.ftlbasic.interceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
//        @Signature(
//                type= Executor.class,
//                method = "query",
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
//        ),
        @Signature(
                type= Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
//@Component
@Slf4j
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        Object[] args = invocation.getArgs();
        //拦截器可以在这里做增强(对创建时间和更新时间做处理)
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
