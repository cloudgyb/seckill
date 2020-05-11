package com.gyb.seckill.service.syslogin;

import com.gyb.seckill.service.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author geng
 * 2020/4/19
 */
@Configuration
public class SysLoginConfig {
    private final SysUserService sysUserService;

    public SysLoginConfig(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Bean
    @Scope("prototype")
    public SysLoginValidatorChain sysLoginValidatorChain(){
        SysLoginValidatorChain chain = new SysLoginValidatorChain();
        chain.addValidator(new UserNameValidator(sysUserService))
                .addValidator(new UserIpValidator());
        return chain;
    }

}
