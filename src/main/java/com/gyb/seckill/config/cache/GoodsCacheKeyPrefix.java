package com.gyb.seckill.config.cache;

/**
 * 商品缓存前缀
 *
 * @author geng
 * 2021/1/9
 */
public class GoodsCacheKeyPrefix extends AbstractCacheKeyPrefix {

    public GoodsCacheKeyPrefix(String id, int expireSeconds) {
        super(id, expireSeconds);
    }

    @Override
    public String getPrefix() {
        return getId();
    }

    public static final GoodsCacheKeyPrefix goodsCachePrefix = new GoodsCacheKeyPrefix("goods:", 60);

    public static final String goodsCachePrefixStr =
            goodsCachePrefix.getPrefix();
}
