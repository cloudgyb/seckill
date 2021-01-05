package com.gyb.seckill.service;

import com.gyb.seckill.dao.OrderInfoDao;
import com.gyb.seckill.entity.OrderInfo;
import com.gyb.seckill.vo.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * 订单服务
 *
 * @author geng
 * 2021/1/4
 */
@Service
public class OrderService {
    private final OrderInfoDao orderInfoDao;

    public OrderService(OrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    public ResponseResult getOrder(long id,int userId) {
        OrderInfo order = orderInfoDao.findByIdAndUserId(id,userId);
        if(order == null)
            return ResponseResult.error("该订单不存在！");
        return ResponseResult.ok(order);
    }

    public OrderInfo getOrderDetail(long id,int userId) {
        return orderInfoDao.findByIdAndUserId(id,userId);
    }
}
