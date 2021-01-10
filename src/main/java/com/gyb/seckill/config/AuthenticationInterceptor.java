package com.gyb.seckill.config;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.config.web.NoLoginException;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.UserService;
import com.gyb.seckill.utils.HttpServletUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器，拦截没有登录的用户访问需要认证的资源（接口）
 *
 * @author geng
 * 2021/1/9
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public AuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod method = (HandlerMethod) handler;
        final RequiredLogin requiredLogin = method.getMethodAnnotation(RequiredLogin.class);
        if (requiredLogin == null)
            return true;
        String token = request.getParameter(UserSessionTokenPrefix.TOKEN);
        if (token == null || "".equals(token)) {
            token = HttpServletUtil.getCookieValue(UserSessionTokenPrefix.TOKEN);
        }
        if (token == null || "".equals(token)) {
            throw new NoLoginException("token 过期！");
        }
        final User user = userService.getByToken(token);
        if (user == null) {
            throw new NoLoginException("session 过期！");
        }
        return true;
    }
}
