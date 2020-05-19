package com.gyb.seckill.service;

import com.gyb.seckill.vo.ResponseResult;
import com.gyb.seckill.controller.form.UserSignUpForm;
import com.gyb.seckill.dao.UserDao;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * 用户注册服务
 *
 * @author geng
 * 2020/5/9
 */
@Service
@Transactional
@Slf4j
public class UserSignUpService {
    private final UserDao userDao;

    public UserSignUpService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseResult signUp(UserSignUpForm form){
        String phone = form.getPhone();
        if(isPhoneExist(phone)){
            return ResponseResult.error("手机号已经被注册!");
        }
        User user = new User();
        user.setNickName(form.getNickName());
        String salt = generatePassSalt(form.getPhone()); //生成随机盐
        user.setPasswordSalt(salt);
        user.setPassword(MD5Util.encode(form.getPassword()+salt));
        user.setAge(form.getAge());
        user.setPhone(phone);
        user.setCreateDate(new Date());
        int n = userDao.save(user);
        if(n != 1){
            log.warn("user sign up failed!id="+user.getPhone());
            return ResponseResult.error("注册失败!",null);
        }else{
            return ResponseResult.ok();
        }
    }

    public boolean isPhoneExist(String phone) {
        int n = userDao.countByPhone(phone);
        return n>0;
    }

    public boolean isNickNameExist(String nickName){
        return  userDao.countByNickName(nickName) > 0;
    }

    private String generatePassSalt(String phone) {
        String uuid = UUID.randomUUID().toString() + phone;
        return MD5Util.encode(uuid);
    }

}
