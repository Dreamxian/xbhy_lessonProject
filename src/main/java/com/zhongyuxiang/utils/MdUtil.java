package com.zhongyuxiang.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @auth zyx
 * @date 2020/3/20 14:33
 * @Description
 */
public class MdUtil {

    //盐值
    private static final String SALT = "abc";

    public static void main(String[] args) {
        System.out.println(md5("admin"));
    }

    public static String md5(String password) {
        String result = "";
        try {
            //md5 sha
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update((password + SALT).getBytes());
            //加密后的密文（32位）
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
