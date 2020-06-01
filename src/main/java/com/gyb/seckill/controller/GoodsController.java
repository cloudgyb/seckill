package com.gyb.seckill.controller;

import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author geng
 * 2020/5/20
 */
@Controller
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping({"/","/goods/list"})
    public String goodsList(){
        return "goods-list";
    }

    @GetMapping({"miaosha/goods/list"})
    @ResponseBody
    public List<MiaoshaGoodsDTO> miaoshaGoodsDTOList(){
        return goodsService.getAllMiaoshaGoods();
    }
}
