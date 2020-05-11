package com.gyb.seckill.service.common;

import com.alibaba.fastjson.JSON;
import com.gyb.seckill.config.CacheKeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 缓存基础服务
 * @author geng
 * 2020/4/18
 */
@Service
public class RedisService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T get(CacheKeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String s = jedis.get(realKey);
            if(s == null)
                return null;
            return JSON.parseObject(s, clazz);
        }finally {
            assert jedis != null;
            jedis.close();
        }
    }
    public <T> void set(CacheKeyPrefix prefix,String key,T t){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String jsonStr;
            if(t instanceof Number || t instanceof String)
                jsonStr = t+"";
            else
                jsonStr = JSON.toJSONString(t);
            String realKey = prefix.getPrefix() + key;
            int expireSeconds = prefix.getExpireSeconds();
            String replyCode;
            if(expireSeconds <= 0)
                replyCode = jedis.set(realKey,jsonStr);
            else
                replyCode = jedis.setex(realKey,expireSeconds,jsonStr);
            logger.info("Jedis set reply code:"+replyCode);
        }finally {
            assert jedis != null;
            jedis.close();
        }
    }

    public void del(CacheKeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            Long del = jedis.del(realKey);
            logger.info("Jedis del reply code:"+del);
        }finally {
            assert jedis != null;
            jedis.close();
        }
    }
}
