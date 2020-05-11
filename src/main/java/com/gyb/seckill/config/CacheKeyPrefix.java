package com.gyb.seckill.config;

/**
 * @author geng
 * 2020/4/19
 */
public interface CacheKeyPrefix {
    int getExpireSeconds();
    String getPrefix();
}
