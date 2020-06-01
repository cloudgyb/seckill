package com.gyb.seckill.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
    @Autowired
    public WebConfig(UserInfoArgumentResolver userInfoArgumentResolver) {
        this.userInfoArgumentResolver = userInfoArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userInfoArgumentResolver);
    }
}
