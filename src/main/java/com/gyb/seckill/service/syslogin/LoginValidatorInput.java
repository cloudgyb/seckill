package com.gyb.seckill.service.syslogin;

import lombok.Getter;
import lombok.Setter;

/**
 * @author geng
 * 2020/4/19
 */

@Getter
@Setter
public class LoginValidatorInput implements ValidatorInput {
    private String account;
    private String password;
    private String checkCode;
    private String ip;
    private Class<? extends LoginType> loginType;
}
