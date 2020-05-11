package com.gyb.seckill.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户注册参数表单
 *
 * @author geng
 * 2020/5/9
 */
@Getter
@Setter
public class UserSignUpForm {
    @NotBlank(message = "手机号不能为空!")
    @Pattern(regexp = "^1[35789][0-9]{9}$",message = "手机号不合法!")
    private String phone;
    @NotBlank(message = "用户名不能为空!")
    private String nickName;
    private int age;
    @NotBlank(message = "密码不能为空!")
    private String password;
}
