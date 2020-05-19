package com.gyb.seckill.service.syslogin;

/**
 * @author geng
 * 2020/4/19
 */
public class UserIpValidator implements Validator {

    @Override
    public void validate(ValidatorInput input, ValidatorOutput output, ValidatorChain chain) {
        LoginValidatorInput loginInput = (LoginValidatorInput) input;
        LoginValidatorOutput loginOutput = (LoginValidatorOutput) output;
        String ip = loginInput.getIp();
        if("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) {
            chain.doValidate(loginInput,loginOutput);
        }else{
            loginOutput.setCode(LoginResult.IP_NOT_ALLOWED.getCode());
            loginOutput.setMess(LoginResult.IP_NOT_ALLOWED.getMess());
        }
    }
}
