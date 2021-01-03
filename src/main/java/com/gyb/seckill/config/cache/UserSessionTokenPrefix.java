package com.gyb.seckill.config.cache;

/**
 * 用户会话前缀
 *
 * @author geng
 * 2020/5/17
 */
public class UserSessionTokenPrefix extends AbstractCacheKeyPrefix {
    public final static String TOKEN = "token";
    public final static int EXPIRE_SECONDS = 3600 * 24 * 2; //有效期两天

    public UserSessionTokenPrefix() {
        super(TOKEN, EXPIRE_SECONDS);
    }

    public static UserSessionTokenPrefix TK = new UserSessionTokenPrefix();

    @Override
    public String getPrefix() {
        return "session:";
    }
}
