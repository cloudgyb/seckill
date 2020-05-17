package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.entity.SysUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geng
 * 2020/4/19
 */

@Setter
@Getter
public class SysLoginValidatorOutput implements ValidatorOutput {
    private int code;
    private String mess;
    private SysUser user;
}
