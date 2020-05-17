package com.gyb.seckill.controller;

import com.gyb.seckill.config.cache.UserCacheKeyPrefix;
import com.gyb.seckill.service.common.RedisService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author geng
 * 2020/4/19
 */
@RestController
@RequestMapping("/redis")
@Validated
public class RedisTestController {
    private final RedisService redisService;

    public RedisTestController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/testSet")
    public String testSet(@NotBlank(message = "参数v不能为空！") String v){
        redisService.set(UserCacheKeyPrefix.ID,"key1","1234");
        return "ok";
    }
    @GetMapping("/testGet")
    public String testGet(){
        return redisService.get(UserCacheKeyPrefix.ID,"key1", String.class);
    }
}
