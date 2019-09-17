package com.cloud.ftl.ftlbasic.service;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 顶层cache类
 * @param <T>
 */
public interface IBaseCache<T> extends IBaseService<T>{

    T cacheSelectById(Serializable id,String... nullErrMsg) ;

    T cacheSelectOne(T query,String... nullErrMsg) ;

    List<T> cacheSelectList(T query,String... emptyErrMsg) ;

    List<T> cacheSelectList(T query, List<String> fieldList, String... emptyErrMsg);

    List<T> cacheSelectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) ;

    PageBean<T> cacheSelectPage(T query);

    PageBean<T> cacheSelectPage(T query, List<String> fieldList);

    Long cacheSelectCount(T query) ;
}
