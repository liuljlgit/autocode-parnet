/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cloud.ftl.ftlbasic.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 顶层mapper类
 * @param <T>
 */
public interface IBaseMapper<T> extends IMapper<T> {

    /**
     * 获取表的最大id
     * @param
     */
    Long selectMaxId();

    /**
     * 根据 ID 查询
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 根据 entity 条件，查询全部记录
     * @param et 查询实体
     */
    List<T> selectList(@Param("et") T entity);

    /**
     * 根据 entity 条件，获取记录总数
     * @param et 查询实体
     */
    Long selectCount(@Param("et") T entity);

    /**
     * 根据 id 列表，获取记录列表
     * @param list id列表
     */
    List<T> selectBatchIds(Collection<? extends Serializable> list);

    /**
     * 更新不为空的属性
     * @param entity
     * @return
     */
    int updateNotNull(@Param("qt") T entity);

    /**
     * 全更新，字段置空也更新
     * @param entity
     * @return
     */
    int updateWithNull(@Param("qt") T entity);

    /**
     * 批量更新，更新不为空的属性
     * @param list
     */
    void updateBatchNotNull(List<T> list);

    /**
     * 批全量更新，字段置空也更新
     * @param list
     */
    void updateBatchWithNull(List<T> list);
}
