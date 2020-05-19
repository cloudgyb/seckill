package com.gyb.seckill.controller;

import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.service.syslogin.LoginType;
import com.gyb.seckill.service.syslogin.UserLoginService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
