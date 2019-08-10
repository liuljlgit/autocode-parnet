package com.cloud.ftl.ftlbasic.service;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * 顶层service类
 * @param <T>
 */
public interface IBaseService<T> extends IService<T>{

    T selectById(Serializable id) throws BusiException;

    T selectOne(T query) throws BusiException;

    PageBean<T> selectPage(T query) throws BusiException;

    List<T> selectList(T query) throws BusiException;

}
