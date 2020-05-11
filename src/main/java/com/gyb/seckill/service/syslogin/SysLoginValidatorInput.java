package com.gyb.seckill.service.syslogin;

import lombok.Getter;
import lombok.Setter;

/**
 * @author geng
 * 2020/4/19
 */

@Getter
@Setter
public class SysLoginValidatorInput implements ValidatorInput {
    private String userId;
    private String userName;
    private String phone;
    private String password;
    private String checkCode;
    private String ip;
}
