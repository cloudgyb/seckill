package com.gyb.seckill.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis 客户端配置
 *
 * @author geng
 * 2020/4/18
 */
@Slf4j
@Configuration
public class RedisConfig {
    @Value("${redis.host:localhost}")
    private String redisHost;
    @Value("${redis.port:6379}")
    private int redisPort;
    @Value("${redis.password:#{null}}")
    private String redisPass;
    @Value("${redis.max-total:1000}")
    private int redisMaxTotal;
    @Value("${redis.max-idle:10}")
    private int redisMaxIdle;
    @Value("${redis.max-wait:3000}")
    private int redisMaxWaitMillis;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMaxWaitMillis(redisMaxWaitMillis);
        final JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost,
                redisPort, 3000, redisPass);
        pingRedis(jedisPool);
        return jedisPool;
    }

    void pingRedis(JedisPool jedisPool){
        try {
            final Jedis jedis = jedisPool.getResource();
            //验证redis是否连接成功
            log.info(String.format("Jedis已成功连接到%s：%d redis服务器！", redisHost, redisPort));
            if(jedis != null)
                jedis.close();
        }catch (JedisConnectionException e){
            log.warn(String.format("Jedis连接到%s:%d redis服务器失败,请检查jedis配置是否正确或者Redis服务是否已启动！",
                    redisHost, redisPort));
            log.warn(e.getMessage());
        }
    }
}
