package com.cloud.ftl.ftlbasic.service;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 顶层service类
 * @param <T>
 */
public interface IBaseService<T> extends IService<T>{

    Long selectMaxId() throws BusiException;

    T selectById(Serializable id) throws BusiException;

    T selectOne(T query) throws BusiException;

    PageBean<T> selectPage(T query) throws BusiException;

    List<T> selectList(T query) throws BusiException;

    Long selectCount(T query) throws BusiException;

    List<T> selectBatchIds(Collection<? extends Serializable> list);

    int updateNotNull(T entity);

    int updateWithNull(T entity);

    void updateBatchNotNull(List<T> list);

    void updateBatchWithNull(List<T> list);

}
