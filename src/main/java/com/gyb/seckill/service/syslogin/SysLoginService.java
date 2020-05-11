package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class SysLoginService {
    private SysLoginConfig sysLoginConfig;

    public SysLoginService(SysLoginConfig sysLoginConfig) {
        this.sysLoginConfig = sysLoginConfig;
    }

    public ResponseResult login(LoginForm form) {
        SysLoginValidatorInput input = new SysLoginValidatorInput();
        SysLoginValidatorOutput output = new SysLoginValidatorOutput();
        input.setUserName(form.getUserName());
        input.setPhone(form.getPhone());
        input.setPassword(form.getPassword());
        input.setCheckCode(form.getCheckCode());
        input.setIp(form.getIp());
        SysLoginValidatorChain chain = sysLoginConfig.sysLoginValidatorChain();
        chain.doValidate(input,output);
        int code = output.getCode();
        String mess = output.getMess();
        if(code == 0){
            return ResponseResult.ok("登录成功！",form.getPhone());
        }
        return ResponseResult.of(code,mess,form.getPhone());
    }

}
