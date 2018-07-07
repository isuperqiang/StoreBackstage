package com.richie.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author richie on 2018.06.25
 */
@Controller
public class HelloController {

    @GetMapping("/helloworld")
    @ResponseBody
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register.html")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/goods_main.html")
    public String goodsMain() {
        return "goods_main";
    }

    @GetMapping("/category_main.html")
    public String categoryMain() {
        return "category_main";
    }

    @GetMapping("/member_main.html")
    public String memberMain() {
        return "member_main";
    }

    @GetMapping("/goods_new.html")
    public String createGoods() {
        return "goods_new";
    }

    @GetMapping("/member_new.html")
    public String createMember() {
        return "member_new";
    }

}
