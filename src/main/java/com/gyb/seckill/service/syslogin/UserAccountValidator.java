package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.UserService;
import com.gyb.seckill.utils.MD5Util;

/**
 * @author geng
 * 2020/4/19
 */
public class UserAccountValidator implements Validator {
    private final UserService userService;

    public UserAccountValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validate(ValidatorInput input, ValidatorOutput output, ValidatorChain chain) {
        LoginValidatorInput loginInput = (LoginValidatorInput) input;
        LoginValidatorOutput loginOutput = (LoginValidatorOutput) output;
        String account = loginInput.getAccount();
        User user = null;
        Class<? extends LoginType> loginType = loginInput.getLoginType();
        if(loginType == LoginType.UserNameLogin.class){
            user = userService.getByUserName(account);
        }else if(loginType == LoginType.PhoneLogin.class){
            user = userService.getByPhone(account);
        }else if(loginType == LoginType.EmailLogin.class){
            user = userService.getByEmail(account);
        }
        if(user != null) {
            String loginPassword = loginInput.getPassword();
            //数据库存储的密码=md5(用户输入密码+注册时候随机生成的盐)
            String realPassword = MD5Util.encode(loginPassword + user.getPasswordSalt());
            if(user.getPassword().equals(realPassword)) {
                loginOutput.setUser(user);
                chain.doValidate(loginInput, loginOutput);
            }
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
