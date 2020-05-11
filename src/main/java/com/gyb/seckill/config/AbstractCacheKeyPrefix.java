package com.gyb.seckill.config;

/**
 * @author geng
 * 2020/4/19
 */
public abstract class AbstractCacheKeyPrefix implements CacheKeyPrefix {
    /**
     * 缓存前缀的唯一标识
     */
    private final String id;
    /**
     * 默认的缓存过期时间，单位：s
     * 如果expireSeconds = 0，则不过期
     */
    private final int expireSeconds;

    public AbstractCacheKeyPrefix(String id) {
        this(id,0);
    }

    public AbstractCacheKeyPrefix(String id, int expireSeconds) {
        this.id = id;
        this.expireSeconds = expireSeconds;
    }


    @Override
    public int getExpireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return this.getClass().getSimpleName()+":"+this.id;
    }
}
