package com.cloud.ftl.ftlbasic.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class RedisUtil {

    /**
     * 生成redis的key值
     * @param t
     * @param totalKey
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String getRedisKey(T t,Boolean totalKey) throws Exception {
        StringBuffer key = new StringBuffer();
        try{
            Class<?> aClass = t.getClass().getSuperclass();
            Class<?> superclass = aClass.getSuperclass();
            String className = t.getClass().getSimpleName();
            Field[] aClassFields = aClass.getDeclaredFields();
            Field[] superclassFields = superclass.getDeclaredFields();
            List<Field> criteriasFields = Arrays.stream(aClassFields).filter(e -> e.getName().equals("criterias")).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(criteriasFields)){
                throw new BusiException("no criterias field");
            }
            Field criteriasField = criteriasFields.get(0);
            Field pageField = null;
            Field pageSizeField = null;
            for(int i=0;i<superclassFields.length;i++){
                if(superclassFields[i].getName().equals("page")){
                    pageField = superclass.getField("page");
                }
                if(superclassFields[i].getName().equals("pageSize")){
                    pageSizeField = superclass.getField("pageSize");
                }
            }
            if(Objects.isNull(pageField)){
                throw new BusiException("no pageField field");
            }
            if(Objects.isNull(pageSizeField)){
                throw new BusiException("no pageSizeField field");
            }
            criteriasField.setAccessible(true);
            pageField.setAccessible(true);
            pageSizeField.setAccessible(true);
            Object criterias = criteriasField.get(t);
            Object page = pageField.get(t);
            Object pageSize = pageSizeField.get(t);
            //拼装key值
            key.append(className);
            if(totalKey){
                key.append(":total");
            }else{
                key.append(":list");
                if(Objects.nonNull(page)){
                    key.append(":page:").append(page.toString());
                }
                if(Objects.nonNull(pageSize)){
                    key.append(":pageSize:").append(pageSize.toString());
                }
            }
            //拼接条件
            JSONArray criteriasArr = JSON.parseArray(JSON.toJSONString(criterias));
            if(Objects.nonNull(criteriasArr)){
                for(int i=0;i<criteriasArr.size();i++){
                    JSONObject object1 = criteriasArr.getJSONObject(i);
                    object1.remove("valid");
                    JSONArray criterions = object1.getJSONArray("criterions");
                    for(int j=0;j<criterions.size();j++){
                        JSONObject object2 = criterions.getJSONObject(j);
                        object2.remove("listValue");
                        object2.remove("noValue");
                        object2.remove("secondValue");
                        object2.remove("oneValue");
                    }
                }
                key.append(":condition:").append(criteriasArr.toJSONString());
            }
            return SecureUtil.md5X16Str(key.toString(),"utf-8");
        } catch (Exception e){
            log.error("error to parse object to redis key");
            throw e;
        }
    }
}
