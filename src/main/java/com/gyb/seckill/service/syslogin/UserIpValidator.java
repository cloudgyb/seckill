package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.entity.SysUser;
import com.gyb.seckill.service.SysUserService;

/**
 * @author geng
 * 2020/4/19
 */
public class UserIpValidator implements Validator {

    @Override
    public void validate(ValidatorInput input, ValidatorOutput output, ValidatorChain chain) {
        SysLoginValidatorInput loginInput = (SysLoginValidatorInput) input;
        SysLoginValidatorOutput loginOutput = (SysLoginValidatorOutput) output;
        String ip = loginInput.getIp();
        if("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) {
            chain.doValidate(loginInput,loginOutput);
        }else{
            loginOutput.setCode(LoginResult.IP_NOT_ALLOWED.getCode());
            loginOutput.setMess(LoginResult.IP_NOT_ALLOWED.getMess());
        }
    }
}
