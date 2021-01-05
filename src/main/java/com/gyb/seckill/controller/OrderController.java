package com.gyb.seckill.controller;

import com.gyb.seckill.entity.Goods;
import com.gyb.seckill.entity.OrderInfo;
import com.gyb.seckill.entity.User;
import com.gyb.seckill.service.GoodsService;
import com.gyb.seckill.service.OrderService;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单
 *
 * @author geng
 * 2021/1/4
 */
@Controller
public class OrderController {
    private final GoodsService goodsService;
    private final OrderService orderService;

    public OrderController(OrderService orderService,
                           GoodsService goodsService) {
        this.orderService = orderService;
        this.goodsService = goodsService;
    }

    @GetMapping("/order")
    @ResponseBody
    public ResponseResult getOrder(@RequestParam long id, User user){
        return orderService.getOrder(id,user.getId());
    }

    @GetMapping("/order/detail")
    public String getOrderInfo(@RequestParam long id, User user, Model m){
        OrderInfo order = orderService.getOrderDetail(id, user.getId());
        m.addAttribute("order",order);
        if(order != null){
            final Goods goods = goodsService.getGoods(order.getGoodsId());
            m.addAttribute("goods",goods);
        }
        return "order-detail";
    }
}
