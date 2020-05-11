package com.gyb.seckill.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author geng
 * 2020/4/19
 */
@Getter
@Setter
public class LoginForm {
    private String userName;

    @NotBlank(message = "手机号不能为空！")
    @Pattern(regexp = "^1[35789]\\d{9}$",message = "号码不合法！")
    private String phone;
    @NotBlank(message = "密码不能为空！")
    private String password;
    private String checkCode;
    private String ip;
}
