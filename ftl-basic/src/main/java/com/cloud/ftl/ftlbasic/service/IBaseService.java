package com.cloud.ftl.ftlbasic.service;

import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 顶层service类
 * @param <T>
 */
public interface IBaseService<T> extends IService<T>{

    Long selectMaxId() ;

    T selectById(Serializable id,String... nullErrMsg) ;

    T selectOne(T query,String... nullErrMsg) ;

    List<T> selectList(T query,String... emptyErrMsg) ;

    List<T> selectList(T query, List<String> fieldList, String... emptyErrMsg);

    List<T> selectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) ;

    PageBean<T> selectPage(T query);

    PageBean<T> selectPage(T query, List<String> fieldList);

    Long selectCount(T query) ;

    int update(T entity,Update... args);

    int updateByMap(T oEntity, Map<String, Object> updateMap);

    void updateBatch(List<T> list,Update... args);

    int add(T entity);

    void addBatch(List<T> list);

    void addBatch(List<T> list, int batchSize);

    void delete(T entity);

    int deleteById(Serializable id);

    void deleteBatchIds(Collection<? extends Serializable> list);

    void save(T t, Update... args);

    void saveBatch(List<T> list, Update... args);
}
