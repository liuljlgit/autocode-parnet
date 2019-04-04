package com.cloud.ftl.ftlbasic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 公共操作类
 * @author lijun
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 复制属性，忽略空值
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 得到空值的属性名称
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        PropertyDescriptor[] var4 = pds;
        int var5 = pds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 生成redis的key
     * 根据不为空的属性和为Boolean类型值为true的属性构成
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> String createRedisKey(T t,boolean isEncrypt,boolean isCountPage){
        *//*得到所有的属性*//*
        List<Field> allField = getAllField(t.getClass(), null);
        *//*过滤掉不能作为key值的field*//*
        List<Field> filterField = allField.stream().filter((Field e) -> {
            try {
                e.setAccessible(true);
                Object obj = e.get(t);
                *//*过滤掉为空的值*//*
                if (Objects.isNull(obj)) {
                    return false;
                }
                *//*过滤掉布尔值为false的值*//*
                if (obj instanceof Boolean && obj == Boolean.FALSE) {
                    return false;
                }
                *//*过滤掉PROP_*//*
                if(e.getName().startsWith("PROP_")){
                    return false;
                }
                *//*过滤掉TABLE_*//*
                if(e.getName().startsWith("TABLE_")){
                    return false;
                }
                *//*如果是统计分页数量,忽略page,pageSize,total,totalPage*//*
                if(isCountPage){
                    if(e.getName().equals("page") || e.getName().equals("pageSize") || e.getName().equals("total")
                            || e.getName().equals("totalPage") || e.getName().equals("index")){
                        return false;
                    }
                }
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            return true;
        }).sorted(Comparator.comparing(Field::getName)).collect(Collectors.toList());
        *//*把属性使用fastjson转换成一个list字符串*//*
        List<String> list = new ArrayList<>();
        for(Field f : filterField)
            try {
                f.setAccessible(true);
                Object obj = f.get(t);
                String name = f.getName();
                String str = name + ":" + JSON.toJSONString(obj);
                list.add(str);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        if(isEncrypt){
            return SecureUtil.md5X16Str(String.join("_",list),"utf-8");
        }else{
            return String.join("_",list);
        }
    }*/

    /**
     * 复杂查询生成加密redis键值
     * @param obj
     * @param isEncrypt
     * @param isCountPage
     * @return
     */
/*    public static String createExampleRedisKey(JSONObject obj,boolean isEncrypt,boolean isCountPage){
        JSONArray criterias = obj.getJSONArray("criterias");
        if(isCountPage){
            obj.remove("page");
            obj.remove("pageSize");
            obj.remove("total");
            obj.remove("totalPage");
            obj.remove("index");
            obj.remove("orderByClause");
        }
        for(int i=0;i<criterias.size();i++){
            JSONObject obj1 = criterias.getJSONObject(i);
            obj1.remove("valid");
            JSONArray criterions = obj1.getJSONArray("criterions");
            for(int j=0;j<criterions.size();j++){
                JSONObject obj2 = criterions.getJSONObject(j);
                obj2.remove("listValue");
                obj2.remove("noValue");
                obj2.remove("secondValue");
                obj2.remove("oneValue");
            }
        }
        String res = obj.toJSONString();
        if(isEncrypt){
            return SecureUtil.md5X16Str(res,"utf-8");
        }else{
            return res;
        }
    }*/

    /**
     * 得到一个对象的所有Field域
     * @param clazz
     * @param fields
     * @return
     */
    /*public static List<Field> getAllField(Class clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            fields.add(field);
        }
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
            getAllField(clazz.getSuperclass(), fields);
        }
        return fields;
    }*/

    /**
     * 对象转数组
     *
     * @param input
     * @return
     */
    public static byte[] transObj2ByteArray(Serializable input) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(input);
            return baos.toByteArray();
        } catch (IOException e) {
            logger.debug("序列化失败", e);
            return null;
        } finally {
            closeOutputStream(oos);
            closeOutputStream(baos);
        }
    }

    /**
     * 数组转换成对象
     *
     * @param input
     * @param clazz
     * @return
     */
    public static <T extends Serializable> T transByteArray2Obj(byte[] input, Class<T> clazz) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        //基本数据类型判断，否则会报错
        if(clazz.equals(Long.class)){
            try{
                T l=clazz.cast(Long.parseLong(new String(input)));
                return l;
            }catch (Exception ex){
            }
        }
        try {
            bis = new ByteArrayInputStream(input);
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            return clazz.cast(obj);
        } catch (NullPointerException ne) {
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("反序列化失败", e);
        } finally {
            closeInpputStream(bis);
            closeInpputStream(ois);
        }
        return null;
    }

    /**
     * 关闭输出流代码
     * @param is
     */
    public static void closeInpputStream(InputStream is) {
        try {
            is.close();
        } catch (Exception e) {
        }
    }

    /**
     * 关闭输出流代码
     * @param os
     */
    public static void closeOutputStream(OutputStream os) {
        try {
            os.close();
        } catch (Exception e) {
        }
    }

    /**
     * 对数组对象进行深度的复制
     * @param src
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
