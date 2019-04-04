package com.cloud.ftl.ftlbasic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 加密工具类
 * @author lijun
 */
public class SecureUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecureUtil.class);

    /**
     * md5加密
     * @param datas
     * @param encoding
     * @return
     */
    public static String md5X16Str(String datas, String encoding) {
        byte[] bytes = md5(datas, encoding);
        StringBuilder md5StrBuff = new StringBuilder();
        String hexString;

        for(int i = 0; i < bytes.length; ++i) {
            hexString = Integer.toHexString(255 & bytes[i]);
            if (hexString.length() == 1) {
                md5StrBuff.append("0").append(hexString);
            } else {
                md5StrBuff.append(hexString);
            }
        }

        return md5StrBuff.toString();
    }

    /**
     * md5加密
     * @param datas
     * @param encoding
     * @return
     */
    public static byte[] md5(String datas, String encoding) {
        try {
            return md5(datas.getBytes(encoding));
        } catch (UnsupportedEncodingException var3) {
            return null;
        }
    }

    /**
     * md5加密
     * @param datas
     * @return
     */
    public static byte[] md5(byte[] datas) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(datas);
            return md.digest();
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }
}
