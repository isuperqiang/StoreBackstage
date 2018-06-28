package com.richie.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author richie on 2018.06.25
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello, world";
    }

    @GetMapping(value = "/login.html")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register.html")
    public String registerPage() {
        return "register";
    }

    @GetMapping(value = "/goods_main.html")
    public String goodsMain() {
        return "goods_main";
    }

    @GetMapping(value = "/goods_edit.html")
    public String goodsEdit() {
        return "goods_edit";
    }

    @GetMapping(value = "/category_main.html")
    public String categoryMain() {
        return "category_main";
    }

}
