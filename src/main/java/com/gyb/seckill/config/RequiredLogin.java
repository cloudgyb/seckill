package com.gyb.seckill.config;

import java.lang.annotation.*;

/**
 * 该注解用于需要认证后才能访问的控制器方法上，或者控制器（Controller）的类上
 * 表示该类的所有方法需要认证后才能访问
 *
 * @author geng
 * 2021/1/9
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequiredLogin {
}
