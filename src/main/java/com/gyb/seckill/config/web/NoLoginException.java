package com.gyb.seckill.config.web;

/**
 * 未登录异常
 *
 * @author geng
 * 2021/1/4
 */
public class NoLoginException extends RuntimeException {
    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }
}
