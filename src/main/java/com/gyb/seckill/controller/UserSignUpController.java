package com.gyb.seckill.controller;

import com.gyb.seckill.controller.form.UserSignUpForm;
import com.gyb.seckill.service.UserSignUpService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册
 *
 * @author geng
 * 2020/5/9
 */
@Controller
public class UserSignUpController {
    private final UserSignUpService signUpService;

    public UserSignUpController(UserSignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/doSignUp")
    @ResponseBody
    public ResponseResult doSignUp(@Validated @RequestBody UserSignUpForm form){
        return signUpService.signUp(form);
    }

}
