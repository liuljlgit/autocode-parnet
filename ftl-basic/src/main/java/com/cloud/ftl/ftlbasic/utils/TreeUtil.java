package com.cloud.ftl.ftlbasic.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Liulj
 * @version v 0.1 2020/4/22 17:01
 */
@Slf4j
public class TreeUtil {

    /**
     * 构建树结构,父ID不允许为空
     * @param all 构建树的数据
     * @param parentId 父ID所在节点例如：PermissionDto::getPId
     * @param id id属性名例如： PermissionDto::getId
     * @param children 子节点属性名例如： PermissionDto::setChildren
     * @return
     */
    public static <T> List<T> tree(List<T> all, Function<T,? extends  Serializable> id, Function<T,? extends  Serializable> parentId, BiConsumer<T, List<T>> children){
        if(CollectionUtils.isEmpty(all)) return Lists.newArrayList();

        Map<Serializable, List<T>> parentMap = all.stream().collect(Collectors.groupingBy(parentId));

        //关联子节点
        all.forEach(t->setChildrenName(id, children, parentMap, t));

        //构建父节点
        all = all.stream().filter(d -> parentId.apply(d).equals(0L)||parentId.apply(d)==null).collect(Collectors.toList());
        return all;
    }


    /**
     * 构建树结构,父ID不允许为空
     * @param all 构建树的数据
     * @param clazz 希望转换为的类型
     * @param parentId 父ID所在节点例如：PermissionDto::getPId
     * @param id id属性名
     * @param children 子节点属性名
     * @return
     */
    public static <B, T> List<B> tree(List<T> all, Class<B> clazz, Function<B, ? extends Serializable> id, Function<B,? extends  Serializable> parentId, BiConsumer<B, List<B>> children){
        if(CollectionUtils.isEmpty(all)) return Lists.newArrayList();

        List<B> bList = all.stream().map(t -> {
            B d;
            try {
                d = clazz.newInstance();
                BeanUtils.copyProperties(t, d);
                return d;
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("拷贝为其他对象失败",e);
                throw new RuntimeException("构建树节点拷贝数据失败");
            }
        }).collect(Collectors.toList());

        Map<Serializable, List<B>> parentMap = bList.stream().collect(Collectors.groupingBy(parentId));

        //关联子节点
        bList.forEach(b->setChildrenName(id, children, parentMap, b));

        //构建父节点
        bList=bList.stream().filter(d -> parentId.apply(d).equals(0L)||parentId.apply(d)==null).collect(Collectors.toList());

        return bList;
    }


    private static <T> void setChildrenName(Function<T,? extends Serializable> id, BiConsumer<T, List<T>> children, Map<? extends Serializable, List<T>> parentMap, T t) {
        Serializable idValue = id.apply(t);
        List<T> ts = parentMap.get(idValue);
        children.accept(t,ts);
    }


}
