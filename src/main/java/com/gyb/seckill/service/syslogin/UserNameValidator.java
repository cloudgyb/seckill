package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.entity.SysUser;
import com.gyb.seckill.service.SysUserService;
import com.gyb.seckill.utils.MD5Util;

/**
 * @author geng
 * 2020/4/19
 */
public class UserNameValidator implements Validator {
    private final SysUserService sysUserService;

    public UserNameValidator(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public void validate(ValidatorInput input, ValidatorOutput output, ValidatorChain chain) {
        SysLoginValidatorInput loginInput = (SysLoginValidatorInput) input;
        SysLoginValidatorOutput loginOutput = (SysLoginValidatorOutput) output;
        String phone = loginInput.getPhone();
        String loginPassword = loginInput.getPassword();
        SysUser user = sysUserService.findByPhone(phone);

        if(user != null) {
            //数据库存储的密码=md5(用户输入密码+注册时候随机生成的盐)
            String realPassword = MD5Util.encode(loginPassword + user.getPasswordSalt());
            if(user.getPassword().equals(realPassword))
                chain.doValidate(loginInput,loginOutput);
            else{
                loginOutput.setCode(LoginResult.USERNAME_OR_PASSWORD_ERROR.getCode());
                loginOutput.setMess(LoginResult.USERNAME_OR_PASSWORD_ERROR.getMess());
            }
        }else{
            loginOutput.setCode(LoginResult.USERNAME_OR_PASSWORD_ERROR.getCode());
            loginOutput.setMess(LoginResult.USERNAME_OR_PASSWORD_ERROR.getMess());
        }
    }
}
