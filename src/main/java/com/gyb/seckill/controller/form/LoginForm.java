package com.gyb.seckill.controller.form;

import com.gyb.seckill.service.syslogin.LoginType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author geng
 * 2020/4/19
 */
@Getter
@Setter
public class LoginForm {
    @NotBlank(message = "账户不能为空!",groups = LoginType.AccountLogin.class)
    private String account;

    @NotBlank(message = "用户名不能为空!",groups = LoginType.UserNameLogin.class)
    private String userName;

    @NotBlank(message = "手机号不能为空！",groups = LoginType.PhoneLogin.class)
    @Pattern(regexp = "^1[35789]\\d{9}$",message = "号码不合法！",groups = LoginType.PhoneLogin.class)
    private String phone;

    @NotBlank(message = "邮箱不能为空!",groups = LoginType.EmailLogin.class)
    @Email(message = "邮箱不合法!",groups = LoginType.EmailLogin.class)
    private String email;

    @NotBlank(message = "密码不能为空！")
    private String password;
    private String checkCode;
    private String ip;

}
