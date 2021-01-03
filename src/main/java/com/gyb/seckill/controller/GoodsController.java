package com.gyb.seckill.controller;

import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.service.GoodsService;
import com.gyb.seckill.vo.MiaoshaGoodsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
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

    @GetMapping({"/","/goods/list"})
    public String goodsList(){
        return "goods-list";
    }

    @GetMapping({"miaosha/goods/list"})
    @ResponseBody
    public List<MiaoshaGoodsDTO> miaoshaGoodsDTOList(){
        return goodsService.getAllMiaoshaGoods();
    }

    @GetMapping("/goods/detail/{id}")
    public ModelAndView goodsDetail(@PathVariable long id, ModelAndView mv){
        MiaoshaGoodsDetail miaoshaGoodsDetail = goodsService.getMiaoshaGoodsDetail(id);
        mv.addObject("detail",miaoshaGoodsDetail);
        mv.setViewName("goods-detail");
        return mv;
    }
}
