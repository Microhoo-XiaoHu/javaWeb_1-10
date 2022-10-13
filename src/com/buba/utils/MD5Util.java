package com.buba.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {

    private MD5Util() {
    }

    /**
     * 加密方法
     * @param password
     * @return
     */
    public static String encrypt(String password){
        return DigestUtils.md5DigestAsHex((password).getBytes());
    }
}
