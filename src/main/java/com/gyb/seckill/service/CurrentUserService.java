package com.gyb.seckill.service;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.config.web.NoLoginException;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.utils.HttpServletUtil;
import org.springframework.stereotype.Service;

/**
 * 当前Session用户服务类
 *
 * @author geng
 * 2021/1/9
 */
@Service
public class CurrentUserService {
    private final UserService userService;

    public CurrentUserService(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentUser() {
        String token = HttpServletUtil.getHttpRequest().
                getParameter(UserSessionTokenPrefix.TOKEN);
        if (token == null || "".equals(token)) {
            token = HttpServletUtil.getCookieValue(UserSessionTokenPrefix.TOKEN);
        }
        if (token == null || "".equals(token)) {
            throw new NoLoginException("token 丢失！");
        }
        return userService.getByToken(token);
    }
}
