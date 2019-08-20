package com.cloud.ftl.ftlbasic.service;

import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 顶层service类
 * @param <T>
 */
public interface IBaseService<T> extends IService<T>{

    Long selectMaxId() throws BusiException;

    T selectById(Serializable id,String... nullErrMsg) throws BusiException;

    T selectOne(T query,String... nullErrMsg) throws BusiException;

    List<T> selectList(T query,String... emptyErrMsg) throws BusiException;

    List<T> selectBatchIds(Collection<? extends Serializable> list,String... emptyErrMsg) throws BusiException ;

    PageBean<T> selectPage(T query) throws BusiException;

    Long selectCount(T query) throws BusiException;

    int update(T entity,Update... args);

    int updateByMap(Map<String,Object> uMap, T oEntity);

    void updateBatch(List<T> list,Update... args);

    int add(T entity);

    void addBatch(List<T> list);

    void delete(T entity);

    int deleteById(Serializable id);

    void deleteBatchIds(Collection<? extends Serializable> list);

    void save(T t, Update... args);
}
