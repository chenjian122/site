package com.sinspark.site.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 * Created by sinspark on 2017/11/16.
 */
public class StringUtils extends org.springframework.util.StringUtils{

    private static final String CHARSET_NAME = "UTF-8";
    private static final String EMPTY = "";

    /**
     * 转换成字节数组
     * @param str
     * @return If NULL OR UnsupportedEncodingException return NULL , Else return bytes[]
     */
    public static byte[] getBytes(String str){
        if(str == null)
            return null;
        try {
            return str.getBytes(CHARSET_NAME);
        }catch (UnsupportedEncodingException uee){
            return null;
        }
    }

    /**
     * 转换为字符串
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes){
        try {
            return new String(bytes,CHARSET_NAME);
        }catch (UnsupportedEncodingException uee){
            return EMPTY;
        }
    }

    /**
     * 如果对象为空，则使用defaultVal值
     * @param obj
     * @param defalutValue
     * @return
     */
    public static String toString(final Object obj,final String defalutValue){
        return obj == null ? defalutValue:obj.toString();
    }

    /**
     * 去除字符串两端的空格，如果为null则返回null
     * @param str
     * @return
     */
    public static String trim(final String str){
        return str == null ? null : str.trim();
    }

    /**
     * 转换为Double
     * @param val
     * @return
     */
    public static Double toDouble(Object val){
        if(val == null){
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        }catch (Exception e){
            return 0D;
        }
    }

    /**
     * 转换为Float
     * @param val
     * @return
     */
    public static Float toFloat(Object val){
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long
     * @param val
     * @return
     */
    public static Long toLong(Object val){
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer
     * @param val
     * @return
     */
    public static Integer toInteger(Object val){
        return toDouble(val).intValue();
    }



    public static void main(String[] args) {
        System.out.println(StringUtils.getBytes("abc"));
        System.out.println(StringUtils.toInteger("abc"));
    }
}
