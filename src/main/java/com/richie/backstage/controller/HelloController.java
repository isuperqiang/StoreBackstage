package com.richie.backstage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author richie on 2018.06.25
 */
@Controller
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("hello")
    @ResponseBody
    public String sayHello(){
        return "hello, world";
    }

}
