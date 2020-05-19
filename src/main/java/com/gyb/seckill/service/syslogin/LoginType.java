package com.gyb.seckill.service.syslogin;

/**
 * @author geng
 * 2020/5/19
 */
public interface LoginType {
    //用于手机号登录时的分组校验
    interface PhoneLogin extends LoginType{}
    //用于用户名登录时的分组校验
    interface UserNameLogin extends LoginType{}
    //用于Email登录时的分组校验
    interface EmailLogin extends LoginType {}
    //用于不确定登录方式时候的分组校验
    interface AccountLogin extends LoginType{}
}
