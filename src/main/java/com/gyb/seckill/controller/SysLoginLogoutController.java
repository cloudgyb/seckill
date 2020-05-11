package com.gyb.seckill.controller;

import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.service.syslogin.SysLoginService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author geng
 * 2020/4/19
 */
@Controller
public class SysLoginLogoutController {
    private final SysLoginService sysLoginService;

    public SysLoginLogoutController(SysLoginService sysLoginService) {
        this.sysLoginService = sysLoginService;
    }

    @GetMapping("/login")
    public String toLoginPage(Model model){
        model.addAttribute("title","登录");
        return "login";
    }


    @PostMapping("/doLogin")
    @ResponseBody
    public ResponseResult login(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        loginForm.setIp(remoteAddr);
        return sysLoginService.login(loginForm);
    }

}
