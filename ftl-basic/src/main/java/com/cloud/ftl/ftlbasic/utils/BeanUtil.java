package com.cloud.ftl.ftlbasic.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Bean拷贝
 *
 * @author lijun
 * @since 1.0.0
 */
@Slf4j
@NoArgsConstructor
public class BeanUtil {

    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = Sets.newHashSet();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if (source != null) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        }
    }

    public static <T, B> T copyOwnerAndSuperProperties(B source, Class<T> target) {
        try {
            return copyOwnerAndSuperProperties(source, target.newInstance());
        } catch (IllegalAccessException | InstantiationException | IllegalArgumentException var3) {
            log.error("类型转换异常：", var3);
            throw new RuntimeException("类型转换异常");
        }
    }

    public static <T, B> T copyOwnerAndSuperProperties(B source, T target) throws IllegalArgumentException, IllegalAccessException {
        Map<Field, Field> equalFields = ReflectUtil.getEqualFields(source.getClass(), target.getClass(), false, true);
        for (Map.Entry<Field, Field> entry : equalFields.entrySet()) {
            Field sourceField = entry.getKey();
            Field targetField = entry.getValue();
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            targetField.set(target,sourceField.get(source));
        }
        return target;
    }

    public static <T, B> List<T> copyBean(List<B> list, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        } else {
            return list.stream().map((b) -> copyBean(b, clazz)).collect(Collectors.toList());
        }
    }

    public static <T> T copyBean(Object source, Class<T> clazz) {
        if (Objects.isNull(source)) {
            return null;
        } else {
            try {
                T t = clazz.newInstance();
                copyOwnerAndSuperProperties(source, t);
                return t;
            } catch (Exception var4) {
                log.error("实体 {} 转换为 {} 类型失败：{}", source, clazz, var4);
                throw new RuntimeException("类型转换失败");
            }
        }
    }
}
