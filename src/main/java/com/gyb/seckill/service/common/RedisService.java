package com.gyb.seckill.service.common;

import com.alibaba.fastjson.JSON;
import com.gyb.seckill.config.cache.CacheKeyPrefix;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class RedisService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T get(CacheKeyPrefix prefix, String key, Class<T> clazz){
        try (Jedis jedis = jedisPool.getResource()) {
            String realKey = prefix.getPrefix() + key;
            String s = jedis.get(realKey);
            if (s == null)
                return null;
            return JSON.parseObject(s, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public <T> T get(String key, Class<T> clazz){
        try (Jedis jedis = jedisPool.getResource()) {
            String s = jedis.get(key);
            if (s == null)
                return null;
            return JSON.parseObject(s, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public <T> void set(CacheKeyPrefix prefix,String key,T t){
        try (Jedis jedis = jedisPool.getResource()) {
            String jsonStr;
            if (t instanceof Number || t instanceof String)
                jsonStr = t + "";
            else
                jsonStr = JSON.toJSONString(t);
            String realKey = prefix.getPrefix() + key;
            int expireSeconds = prefix.getExpireSeconds();
            String replyCode;
            if (expireSeconds <= 0)
                replyCode = jedis.set(realKey, jsonStr);
            else
                replyCode = jedis.setex(realKey, expireSeconds, jsonStr);
            logger.info("Jedis set reply code:" + replyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public <T> void set(String key,T t,int expireSeconds){
        try (Jedis jedis = jedisPool.getResource()) {
            String jsonStr;
            if (t instanceof Number || t instanceof String)
                jsonStr = t + "";
            else
                jsonStr = JSON.toJSONString(t);
            String replyCode;
            if (expireSeconds <= 0)
                replyCode = jedis.set(key, jsonStr);
            else
                replyCode = jedis.setex(key, expireSeconds, jsonStr);
            logger.info("Jedis set reply code:" + replyCode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void del(CacheKeyPrefix prefix,String key){
        try (Jedis jedis = jedisPool.getResource()) {
            String realKey = prefix.getPrefix() + key;
            Long del = jedis.del(realKey);
            logger.info("Jedis del reply code:" + del);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public Long decr(String key){
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.decr(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    public Long incr(String key){
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }
}
