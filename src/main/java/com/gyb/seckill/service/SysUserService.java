package com.gyb.seckill.service;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.dao.SysUserDao;
import com.gyb.seckill.entity.SysUser;
import com.gyb.seckill.service.common.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class SysUserService {
    private final SysUserDao sysUserDao;
    private final RedisService redisService;

    public SysUserService(SysUserDao sysUserDao,RedisService redisService) {
        this.sysUserDao = sysUserDao;
        this.redisService = redisService;
    }

    public SysUser findByPhone(String phone){
        return sysUserDao.findByPhone(phone);
    }

    public SysUser getByToken(String token){
        if(StringUtils.hasText(token))
            return redisService.get(UserSessionTokenPrefix.TK, token, SysUser.class);
        return null;
    }
}
