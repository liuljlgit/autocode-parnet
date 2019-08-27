package com.cloud.ftl.ftlbasic.func;
import java.util.Map;

/**
 * 回调函数入参
 */
public interface FuncMap {

    /**
     * 执行函数
     *
     * @return 函数执行结果
     * @throws Exception 自定义异常
     */
    Map<String,Object> call() throws RuntimeException;

}
