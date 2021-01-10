package com.gyb.seckill.controller;

import com.gyb.seckill.service.GoodsService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品
 *
 * @author geng
 * 2020/5/20
 */
@Controller
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping({"/miaosha/goods/list"})
    @ResponseBody
    public ResponseResult miaoshaGoodsDTOList() {
        return ResponseResult.ok(goodsService.getAllMiaoshaGoods());
    }

    @GetMapping("/miaosha/goods/detail/{id}")
    @ResponseBody
    public ResponseResult goodsDetail(@PathVariable long id) {
        return ResponseResult.ok(goodsService.getMiaoshaGoodsDetail(id));
    }
}
