package com.cloud.ftl.ftlbasic.utils;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.query.ConditGroup;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 根据entity对象生成redisKey
 * @author lijun
 */
@Slf4j
public class QueryKeyUtil {

    private static Method conditGroupsMet;

    static {
        try {
            conditGroupsMet = BaseQuery.class.getDeclaredMethod("getConditGroups");
        } catch (NoSuchMethodException e) {
            conditGroupsMet = null;
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> String getQueryKey(T t,Boolean isPage,String... tableFields){
        try {
            List<ConditGroup> conditGroups = (List<ConditGroup>) Objects.requireNonNull(conditGroupsMet).invoke(t);
            StringBuilder conditionStr = new StringBuilder("");
            StringBuilder fieldStr = new StringBuilder("");
            conditGroups.forEach(e -> conditionStr.append(e.getConditionRedisKey()));
            List<Field> fields = ReflectUtil.getFields(t.getClass(), true, true);
            fields.stream()
                    .filter(field -> Modifier.isPrivate(field.getModifiers()))
                    .filter(field -> {
                        try {
                            field.setAccessible(true);
                            Object o = field.get(t);
                            return Objects.nonNull(o);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .forEach(field -> {
                        try {
                            fieldStr.append(Opt.AND.getCode()).append(field.getName())
                                    .append(Opt.EQUAL.getCode()).append(field.get(t));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
            //field域
            StringBuilder sqlField = new StringBuilder("SQL_FIELD:");
            if(tableFields.length == 0){
                //代表查询所有的域
                sqlField.append("-1");
            } else {
                Arrays.stream(tableFields).forEach(sqlField::append);
            }
            //查询条件
            String sqlCondition = "SQL_CONDITION:" + fieldStr.toString() + conditionStr.toString();
            //分页结果
            StringBuilder sqlKey = sqlField.append(":").append(sqlCondition);
            if(isPage){
                Object page = FieldCacheUtil.getPMet.invoke(t);
                Object pageSize = FieldCacheUtil.getPSMet.invoke(t);
                if(Objects.isNull(page) || Objects.isNull(pageSize)){
                    throw new BusiException("请设置正确的分页值和分页大小");
                }
                sqlKey = sqlKey.append(":SQL_PAGE:").append("PAGE:").append(page.toString()).append(":PAGESIZE:").append(pageSize.toString());
            }
            log.info("SQL_KEY = {}",sqlKey.toString());
            return SecureUtil.md5X16Str(sqlKey.toString(),"utf-8");
        } catch (Exception e) {
            log.error("获取rediskey失败",e);
            throw new BusiException(e.getMessage());
        }
    }
}
