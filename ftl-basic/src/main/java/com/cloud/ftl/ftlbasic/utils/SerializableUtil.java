package com.cloud.ftl.ftlbasic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializableUtil {

    private static final Logger logger = LoggerFactory.getLogger(SerializableUtil.class);

    /**
     * 对象转数组
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
}
