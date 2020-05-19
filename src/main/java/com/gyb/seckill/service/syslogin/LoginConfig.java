package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author geng
 * 2020/4/19
 */
@Configuration
public class LoginConfig {
    private final UserService userService;

    public LoginConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @Scope("prototype")
    public LoginValidatorChain sysLoginValidatorChain(){
        LoginValidatorChain chain = new LoginValidatorChain();
        chain.addValidator(new UserAccountValidator(userService))
                .addValidator(new UserIpValidator());
        return chain;
    }

}
