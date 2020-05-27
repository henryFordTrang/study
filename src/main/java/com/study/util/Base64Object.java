package com.study.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 转换编码
 */
public class Base64Object {
    /**
     * 把base64的String码转换成正常显示的字符串
     */
    public static String base64ToString(String base64) {
        byte[] decode = Base64Util.decode(base64);
        String s = new String(decode);
        return s;
    }
    /**
     * 把String的转换成base64码
     */
    public static String stringToBase64(String ss) {
        byte[] bytes = ss.getBytes();
        String encode = Base64Util.encode(bytes);
        return encode;
    }
}
