package com.richie.backstage.util;

import java.security.MessageDigest;

/**
 * @author richie on 2018.04.14
 */
public class MD5Utils {

    /**
     * md5加密
     *
     * @param text 原文
     * @return 密文
     */
    public static String encode(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 加密转换
            byte[] digest = md.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                // 取低8位 取正
                int a = b & 0xff;
                String hexString = Integer.toHexString(a);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            // ignored
        }
        return "";
    }
}
