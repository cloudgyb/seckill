package com.gyb.seckill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义的Redis配置
 *
 * @author geng
 * 2021/01/20 15:09
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfigProperties {
    private String host = "localhost";
    private int port = 6379;
    private String password;
    private int maxTotal = 1000;
    private int maxIdle = 10;
    private int maxWaitMillis = 3000;
}
