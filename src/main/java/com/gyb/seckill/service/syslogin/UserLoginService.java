package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.common.RedisService;
import com.gyb.seckill.utils.HttpServletUtil;
import com.gyb.seckill.vo.ResponseResult;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class UserLoginService {
    private final LoginConfig loginConfig;
    private final RedisService redisService;

    public UserLoginService(LoginConfig loginConfig, RedisService redisService) {
        this.loginConfig = loginConfig;
        this.redisService = redisService;
    }

    public ResponseResult login(LoginForm form) {
        LoginValidatorInput input = new LoginValidatorInput();
        LoginValidatorOutput output = new LoginValidatorOutput();
        determineLoginType(input,form);
        LoginValidatorChain chain = loginConfig.sysLoginValidatorChain();
        chain.doValidate(input,output);
        int code = output.getCode();
        String mess = output.getMess();
        if(code == 0){
            String token = saveSession(output);//保存用户会话信息
            return ResponseResult.ok("登录成功！",token);
        }
        return ResponseResult.of(code,mess,form.getPhone());
    }

    private String saveSession(LoginValidatorOutput output) {
        User user = output.getUser();
        String token = UUID.randomUUID().toString();
        redisService.set(UserSessionTokenPrefix.TK,token,user);
        HttpServletResponse httpResponse = HttpServletUtil.getHttpResponse();
        Cookie cookie = new Cookie(UserSessionTokenPrefix.TOKEN, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        //cookie.setMaxAge(UserSessionTokenPrefix.EXPIRE_SECONDS);
        //cookie.setSecure(true);
        httpResponse.addCookie(cookie);
        return token;
    }

    public void determineLoginType(LoginValidatorInput input,LoginForm form){
        String phone = form.getPhone();
        String userName = form.getUserName();
        String email = form.getEmail();
        String account;
        if(StringUtils.hasText(phone)){
            account = phone;
            input.setLoginType(LoginType.PhoneLogin.class);
        }else if(StringUtils.hasText(userName)){
            account = userName;
            input.setLoginType(LoginType.UserNameLogin.class);
        }else if(StringUtils.hasText(email)){
            account = email;
            input.setLoginType(LoginType.EmailLogin.class);
        }else{
            account = form.getAccount();
            if(account.matches("^1[35789]\\d{9}$")){
                input.setLoginType(LoginType.PhoneLogin.class);
            }else if(account.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
                input.setLoginType(LoginType.EmailLogin.class);
            }else{
                input.setLoginType(LoginType.UserNameLogin.class);
            }
        }
        input.setAccount(account);
        input.setPassword(form.getPassword());
        input.setCheckCode(form.getCheckCode());
        input.setIp(form.getIp());
    }


}
