package com.gyb.seckill.config;

import lombok.extern.slf4j.Slf4j;
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
    private final RedisConfigProperties redisConfigProperties;

    public RedisConfig(RedisConfigProperties redisConfigProperties) {
        this.redisConfigProperties = redisConfigProperties;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfigProperties.getMaxWaitMillis());
        final JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfigProperties.getHost(),
                redisConfigProperties.getPort(), 3000, redisConfigProperties.getPassword());
        pingRedis(jedisPool);
        return jedisPool;
    }

    void pingRedis(JedisPool jedisPool){
        try {
            final Jedis jedis = jedisPool.getResource();
            //验证redis是否连接成功
            log.info(String.format("Jedis已成功连接到%s：%d redis服务器！",
                    redisConfigProperties.getHost(), redisConfigProperties.getPort()));
            if(jedis != null)
                jedis.close();
        }catch (JedisConnectionException e){
            log.warn(String.format("Jedis连接到%s:%d redis服务器失败,请检查jedis配置是否正确或者Redis服务是否已启动！",
                    redisConfigProperties.getHost(), redisConfigProperties.getPort()));
            log.warn(e.getMessage());
        }
    }
}
