package com.gyb.seckill.config.web;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.UserService;
import com.gyb.seckill.utils.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author geng
 * 2020/5/30
 */
@Component
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    @Autowired
    public UserInfoArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getParameterType();
        return declaringClass == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory){
        String token = nativeWebRequest.getParameter(UserSessionTokenPrefix.TOKEN);
        if(token == null || "".equals(token)){
            token = HttpServletUtil.getCookieValue(UserSessionTokenPrefix.TOKEN);
        }
        return userService.getByToken(token);
    }
}
