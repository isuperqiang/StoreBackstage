package com.richie.backstage.config;

import com.richie.backstage.handler.CustomInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author richie on 2018.06.25
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + uploadDir);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/*", "/hello", "/upload/*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/goods_main.html").setViewName("goods_main");
        registry.addViewController("/index.html").setViewName("goods_main");
        registry.addViewController("/category_main.html").setViewName("category_main");
        registry.addViewController("/goods_edit.html").setViewName("goods_edit");
        registry.addViewController("/goods_new.html").setViewName("goods_new");
        registry.addViewController("/member_main.html").setViewName("member_main");
    }

}
