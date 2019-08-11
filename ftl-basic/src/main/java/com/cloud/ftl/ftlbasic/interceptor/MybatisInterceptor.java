package com.cloud.ftl.ftlbasic.interceptor;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.List;
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
@Component
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

    //检查域是否存在并且设置默认值
    private <T> void checkContailAndSetField(T t,String fieldName,Object value){
        if(containField(t,fieldName)){
            try {
                Field ctField = t.getClass().getDeclaredField(fieldName);
                setFieldValue(ctField,t,value);
            } catch (NoSuchFieldException e) {

            }
        }
    }

    private <T> void checkContailAndSetField(List<T> list, String fieldName, Object value){
        if(containField(list.get(0),fieldName)){
            try {
                for (T t : list) {
                    Field ctField = t.getClass().getDeclaredField(fieldName);
                    setFieldValue(ctField,t,value);
                }
            } catch (NoSuchFieldException e) {

            }
        }
    }

    //判断域是否存在
    private <T> Boolean containField(T t,String fieldName){
        try{
            t.getClass().getDeclaredField(fieldName);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //给反射域设置值
    private void setFieldValue(Field field,Object objVal,Object fieldVal){
        field.setAccessible(true);
        try {
            field.set(objVal,fieldVal);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException("反射设定值失败");
        }
    }
}
