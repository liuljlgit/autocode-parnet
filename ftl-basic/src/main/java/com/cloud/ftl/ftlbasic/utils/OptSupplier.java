package com.cloud.ftl.ftlbasic.utils;

/**
 * 回调函数入参
 * @param <R>
 */
public interface OptSupplier<R> {

    /**
     * 执行函数
     *
     * @return 函数执行结果
     * @throws Exception 自定义异常
     */
    R call(R r) throws Exception;

}
