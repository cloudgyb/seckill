package com.gyb.seckill.controller;

import com.gyb.seckill.config.RequiredLogin;
import com.gyb.seckill.entity.Goods;
import com.gyb.seckill.entity.OrderInfo;
import com.gyb.seckill.service.GoodsService;
import com.gyb.seckill.service.OrderService;
import com.gyb.seckill.vo.OrderDetailVO;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Controller;
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

    @RequiredLogin
    @GetMapping("/order")
    @ResponseBody
    public ResponseResult getOrder(@RequestParam long id) {
        return orderService.getOrder(id);
    }

    @RequiredLogin
    @GetMapping("/order/detail")
    @ResponseBody
    public ResponseResult getOrderInfo(@RequestParam long id) {
        OrderInfo order = orderService.getOrderDetail(id);
        final OrderDetailVO vo = new OrderDetailVO();
        vo.setOrderInfo(order);
        if (order != null) {
            final Goods goods = goodsService.getGoods(order.getGoodsId());
            vo.setGoods(goods);
        }
        return order == null ?
                ResponseResult.error("订单不存在或已删除！") : ResponseResult.ok(vo);
    }
}
