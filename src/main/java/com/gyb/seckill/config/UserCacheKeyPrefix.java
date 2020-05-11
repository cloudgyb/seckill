package com.gyb.seckill.config;

/**
 * @author geng
 * 2020/4/19
 */
public class UserCacheKeyPrefix extends AbstractCacheKeyPrefix {
    private UserCacheKeyPrefix(String id) {
        super(id);
    }
    private UserCacheKeyPrefix(String id, int expireSeconds) {
        super(id, expireSeconds);
    }
    public static UserCacheKeyPrefix ID = new UserCacheKeyPrefix("id");
    public static UserCacheKeyPrefix NAME = new UserCacheKeyPrefix("NAME");
}
