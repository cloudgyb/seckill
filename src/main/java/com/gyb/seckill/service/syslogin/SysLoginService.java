package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.config.cache.UserSessionTokenPrefix;
import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.entity.SysUser;
import com.gyb.seckill.service.common.RedisService;
import com.gyb.seckill.utils.HttpServletUtil;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author geng
 * 2020/4/19
 */
@Service
public class SysLoginService {
    private final SysLoginConfig sysLoginConfig;
    private final RedisService redisService;

    public SysLoginService(SysLoginConfig sysLoginConfig,RedisService redisService) {
        this.sysLoginConfig = sysLoginConfig;
        this.redisService = redisService;
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
            saveSession(output);//保存用户会话信息
            return ResponseResult.ok("登录成功！",form.getPhone());
        }
        return ResponseResult.of(code,mess,form.getPhone());
    }

    private void saveSession(SysLoginValidatorOutput output) {
        SysUser user = output.getUser();
        String token = UUID.randomUUID().toString();
        redisService.set(UserSessionTokenPrefix.TK,token,user);
        HttpServletResponse httpResponse = HttpServletUtil.getHttpResponse();
        Cookie cookie = new Cookie(UserSessionTokenPrefix.TOKEN, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(UserSessionTokenPrefix.EXPIRE_SECONDS);
        //cookie.setSecure(true);
        httpResponse.addCookie(cookie);
    }


}
