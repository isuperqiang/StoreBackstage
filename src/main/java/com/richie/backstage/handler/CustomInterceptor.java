package com.richie.backstage.handler;

import com.richie.backstage.config.Constant;
import com.richie.backstage.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author richie on 2018.04.23
 * 自定义拦截器
 * 执行顺序：pre --> post --> complete
 */
public class CustomInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("preHandler");
        // 返回 true 不做拦截
        // get 请求不拦截
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 检查 token
        Cookie cookie = WebUtils.getCookie(request, Constant.USER_TOKEN);
        if (cookie != null) {
            String token = cookie.getValue();
            logger.info("token:{}, url:{}", token, request.getRequestURL());
            if (Constant.NULL_TOKEN.equals(token) || !ApiUtils.checkLogin(token, request)) {
                logger.info("not login, redirect");
                response.sendRedirect("/login.html");
                return false;
            }
        } else {
            logger.info("not login, redirect");
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle: " + httpServletRequest.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("afterCompletion: " + httpServletRequest.getRequestURL());
    }
}
