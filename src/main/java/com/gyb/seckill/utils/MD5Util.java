package com.gyb.seckill.utils;

import org.springframework.util.DigestUtils;

import java.util.Base64;

/**
 * @author geng
 * 2020/5/9
 */
public final class MD5Util {
    public static String encode(String src){
        byte[] bytes = DigestUtils.md5Digest(src.getBytes());
        byte[] encode = Base64.getEncoder().encode(bytes);
        return new String(encode);
    }

    public static void main(String[] args) {
        String encode = encode("123456dsdfdsfsdfsdfsf");
        System.out.println(encode);
    }
}
