package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geng
 * 2020/4/19
 */

@Setter
@Getter
public class LoginValidatorOutput implements ValidatorOutput {
    private int code;
    private String mess;
    private User user;
}
