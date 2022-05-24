package com.winterhold.mvc.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("forward:/index");
        registry.addViewController("/home").setViewName("forward:/home/index");
        registry.addViewController("/author").setViewName("forward:/author/index");
        registry.addViewController("/book").setViewName("forward:/book/index");
        registry.addViewController("/customer").setViewName("forward:/customer/index");
        registry.addViewController("/login").setViewName("forward:/account/login-form");
        registry.addViewController("/register").setViewName("forward:/account/register-form");
        registry.addViewController("/account").setViewName("forward:/account/index");
        registry.addViewController("/access-denied").setViewName("forward:/account/access-denied");
    }
}
