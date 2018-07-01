package com.richie.backstage.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author richie on 2018.07.01
 */
@Controller
public class StageErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 401) {
            return "/error/401";
        } else if (statusCode == 404) {
            return "/error/404";
        } else if (statusCode == 403) {
            return "/error/403";
        } else {
            return "/error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
