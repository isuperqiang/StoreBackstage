package com.richie.backstage.controller;

import com.richie.backstage.domain.Result;
import com.richie.backstage.domain.User;
import com.richie.backstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        User user = userService.login(phone, password);
        if (user != null) {
            return Result.createYesResult(user);
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
}
