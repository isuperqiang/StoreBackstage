package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.User;
import com.richie.backstage.handler.Result;
import com.richie.backstage.service.UserService;
import com.richie.backstage.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author richie on 2018.06.25
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletRequest request,
                        HttpServletResponse response) {
        User user = userService.login(phone, password);
        if (user != null) {
            HttpSession httpSession = request.getSession();
            int expiry = 3600 * 24;
            String uuid = ApiUtils.getUUID();
            httpSession.setMaxInactiveInterval(expiry);
            httpSession.setAttribute(uuid, user.getUserId());

            Cookie cookie = new Cookie(Constant.USER_TOKEN, uuid);
            cookie.setMaxAge(expiry); // 单位 秒
            cookie.setPath("/");
            response.addCookie(cookie);
            return Result.createYesResult(uuid);
        } else {
            return Result.createNoResult(Result.ErrorCode.LOGIN_FAILED);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        boolean registered = userService.isRegistered(phone);
        if (registered) {
            return Result.createNoResult(Result.ErrorCode.REPEATED_PHONE);
        } else {
            if (userService.register(phone, password)) {
                return Result.createYesResult();
            } else {
                return Result.createNoResult(Result.ErrorCode.REGISTER_FAILED);
            }
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public Result logout(@CookieValue(value = Constant.USER_TOKEN, defaultValue = Constant.NULL_TOKEN) String token,
                         HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.removeAttribute(token);
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Constant.USER_TOKEN.equals(cookie.getName())) {
                Cookie c = new Cookie(cookie.getName(), "");
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        return Result.createYesResult();
    }

    @ResponseBody
    @PostMapping(value = "/query_user")
    public Result queryUserInfo(@CookieValue(value = Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        User user = userService.findUserById(userId);
        if (user != null) {
            return Result.createYesResult(user);
        } else {
            return Result.createNoResult(Result.ErrorCode.USER_NOT_EXIST);
        }
    }
}
