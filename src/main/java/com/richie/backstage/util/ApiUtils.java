package com.richie.backstage.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author richie on 2018.04.14
 */
public class ApiUtils {

    /**
     * 生成 32 位 UUID，eg: b6742abc37714588b9c04e5f5ab0afe1
     *
     * @return
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static boolean checkLogin(String token, HttpServletRequest request) {
        Object session = WebUtils.getSessionAttribute(request, token);
        return session != null && ((int) session) > 0;
    }
}
