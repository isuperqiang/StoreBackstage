package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.Store;
import com.richie.backstage.domain.User;
import com.richie.backstage.handler.Result;
import com.richie.backstage.service.StoreService;
import com.richie.backstage.service.UserService;
import com.richie.backstage.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author richie on 2018.06.25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private StoreService storeService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // {"phone":"15868825645","password":"123456"}
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User u,
                        HttpServletResponse response, HttpSession httpSession) {
        User user = userService.login(u.getPhone(), u.getPassword());
        if (user != null) {
            String uuid = ApiUtils.getUUID();
            httpSession.setAttribute(uuid, user.getUserId());

            int expiry = 3600 * 24;
            Cookie cookie = new Cookie(Constant.USER_TOKEN, uuid);
            cookie.setMaxAge(expiry); // 单位 秒
            cookie.setPath("/");
            response.addCookie(cookie);

            Store store = storeService.queryStore(user.getUserId());
            Cookie cookieStore = new Cookie("store-name", store.getName());
            cookieStore.setMaxAge(expiry);
            cookieStore.setPath("/");
            response.addCookie(cookieStore);
            return Result.createYesResult(uuid);
        } else {
            return Result.createNoResult(Result.ErrorCode.LOGIN_FAILED);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        boolean registered = userService.isRegistered(user.getPhone());
        if (registered) {
            return Result.createNoResult(Result.ErrorCode.REPEATED_PHONE);
        } else {
            if (userService.register(user.getPhone(), user.getPassword())) {
                return Result.createYesResult();
            } else {
                return Result.createNoResult(Result.ErrorCode.REGISTER_FAILED);
            }
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public Result logout(@CookieValue(value = Constant.USER_TOKEN, defaultValue = Constant.NULL_TOKEN) String token,
                         HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
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
