package com.gyb.seckill.controller;

import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.SecondsKillService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品秒杀
 *
 * @author geng
 * 2021/1/4
 */
@RestController
public class SecondsKillController {
    private final SecondsKillService secondsKillService;

    public SecondsKillController(SecondsKillService secondsKillService) {
        this.secondsKillService = secondsKillService;
    }

    @PostMapping("/secondsKill")
    public ResponseResult secondsKill(@RequestParam long goodsId, User user){
        return secondsKillService.secondsKill(goodsId,user.getId());
    }
}
