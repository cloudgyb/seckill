package com.gyb.seckill.service;

import com.gyb.seckill.dao.SysUserDao;
import com.gyb.seckill.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class SysUserService {
    private final SysUserDao sysUserDao;

    public SysUserService(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public int count(String userName, String password) {
        return 0;
    }

    public SysUser findByPhone(String phone){
        return sysUserDao.findByPhone(phone);
    }
}
