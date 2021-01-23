package com.gyb.seckill.config.web;

import com.gyb.seckill.config.AuthenticationInterceptor;
import com.gyb.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web 配置类
 *
 * @author geng
 * 2020/5/30
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final UserInfoArgumentResolver userInfoArgumentResolver;
    private final UserService userService;

    @Autowired
    public WebConfig(UserInfoArgumentResolver userInfoArgumentResolver,
                     UserService userService) {
        this.userInfoArgumentResolver = userInfoArgumentResolver;
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(userService))
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("*.htm");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userInfoArgumentResolver);
    }
}
