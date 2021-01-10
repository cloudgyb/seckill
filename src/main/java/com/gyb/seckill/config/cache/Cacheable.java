package com.gyb.seckill.config.cache;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存支持注解
 *
 * @author geng
 * 2021/1/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    String cacheName();

    String[] key() default {};

    int expireTime() default 0;
}
