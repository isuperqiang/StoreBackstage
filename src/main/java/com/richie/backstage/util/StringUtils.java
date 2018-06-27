package com.richie.backstage.util;

/**
 * @author richie on 2018.06.27
 */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
