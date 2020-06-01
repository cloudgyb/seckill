package com.gyb.seckill.service;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.dao.UserDao;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.common.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class UserService {
    private final UserDao sysUserDao;
    private final RedisService redisService;

    public UserService(UserDao sysUserDao, RedisService redisService) {
        this.sysUserDao = sysUserDao;
        this.redisService = redisService;
    }

    public User getByPhone(String phone){
        return sysUserDao.findByPhone(phone);
    }

    public User getByUserName(String userName) {
        return sysUserDao.findByUserName(userName);
    }
    public User getByEmail(String email) {
        return sysUserDao.findByEmail(email);
    }

    public User getByToken(String token){
        if(StringUtils.hasText(token)) {
            User user = redisService.get(UserSessionTokenPrefix.TK, token, User.class);
            if(user != null){ //刷新token过期时间
                redisService.set(UserSessionTokenPrefix.TK,token,user);
            }
            return user;
        }
        return null;
    }

}
