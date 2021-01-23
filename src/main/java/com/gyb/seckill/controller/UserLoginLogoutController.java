package com.gyb.seckill.controller;

import com.gyb.seckill.controller.form.LoginForm;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.CurrentUserService;
import com.gyb.seckill.service.syslogin.LoginType;
import com.gyb.seckill.service.syslogin.UserLoginService;
import com.gyb.seckill.vo.ResponseResult;
import com.gyb.seckill.vo.UserVo;
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
    private final CurrentUserService currentUserService;

    public UserLoginLogoutController(UserLoginService userLoginService, CurrentUserService currentUserService) {
        this.userLoginService = userLoginService;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public ResponseResult login(@RequestBody
                                @Validated(LoginType.AccountLogin.class) LoginForm loginForm,
                                HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        loginForm.setIp(remoteAddr);
        return userLoginService.login(loginForm);
    }

    @GetMapping("/getLoginUserInfo")
    @ResponseBody
    public ResponseResult getLoginUserInfo() {
        final User currentUser = currentUserService.getCurrentUser();
        UserVo user = null;
        if (currentUser != null) {
            user = new UserVo();
            user.setUserId(currentUser.getId());
            user.setNickName(currentUser.getNickName());
            user.setPhone(currentUser.getPhone());
            user.setAge(currentUser.getAge());
            user.setSignUpDate(currentUser.getCreateDate());
        }
        return ResponseResult.ok(user);
    }


    @PostMapping("/doLogout")
    @ResponseBody
    public ResponseResult logout(@RequestParam(value = "token", required = false) String paramToken,
                                 @CookieValue(value = "token", required = false) String cookieToken) {
        String token = "";
        if (StringUtils.hasText(paramToken)) {
            token = paramToken;
        } else if (StringUtils.hasText(cookieToken)) {
            token = cookieToken;
        }
        userLoginService.logout(token);
        return ResponseResult.ok();
    }
}
