package com.gyb.seckill.controller;

import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.service.syslogin.LoginType;
import com.gyb.seckill.service.syslogin.UserLoginService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geng
 * 2020/4/19
 */
@Controller
public class UserLoginLogoutController {
    private final UserLoginService userLoginService;

    public UserLoginLogoutController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String toLoginPage(Model model){
        model.addAttribute("title","登录");
        return "login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public ResponseResult login(@RequestBody
                                    @Validated(LoginType.PhoneLogin.class) LoginForm loginForm,
                                HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        loginForm.setIp(remoteAddr);
        return userLoginService.login(loginForm);
    }

    @PostMapping("/doLogout")
    @ResponseBody
    public ResponseResult logout(@RequestParam(value = "token",required = false) String paramToken,
                                 @CookieValue("token") String cookieToken){
        String token = "";
        if(StringUtils.hasText(paramToken)){
            token = paramToken;
        }else if(StringUtils.hasText(cookieToken)){
            token = cookieToken;
        }
        userLoginService.logout(token);
        return ResponseResult.ok();
    }

}
