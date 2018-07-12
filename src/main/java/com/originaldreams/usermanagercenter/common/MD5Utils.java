package com.originaldreams.usermanagercenter.common;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Utils {

    /**
     * 加密字符串
     * @param str 原字符串
     * @return  加密后的字符串
     * @throws Exception 加密过程中抛的异常
     */
    public static String EncoderByMd5(String str) throws Exception{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 检查原字符串和加密后的字符串是否匹配
     * @param str   原字符串
     * @param encodeStr 加密后的字符串
     * @return 匹配 true,不匹配 false
     * @throws Exception 加密过程中可能抛异常
     */
    public static boolean checkqual(String str,String encodeStr) throws Exception{
        if(EncoderByMd5(str).equals(encodeStr))
            return true;
        else
            return false;
    }

}
