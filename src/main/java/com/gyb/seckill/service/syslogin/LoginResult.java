package com.gyb.seckill.service.syslogin;

import lombok.Getter;

/**
 * @author geng
 * 2020/4/19
 */
@Getter
public enum LoginResult {
    USERNAME_OR_PASSWORD_ERROR(-1,"账号或密码错误！"),
    CHECK_CODE_ERROR(-2,"验证码错误！"),
    IP_NOT_ALLOWED(-3,"IP不允许登录"),
    IP_LOCKED(-4,"ip被锁定！");

    private final int code;
    private final String mess;

    LoginResult(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }
}
