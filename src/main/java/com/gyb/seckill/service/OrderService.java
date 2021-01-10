package com.gyb.seckill.service;

import com.gyb.seckill.dao.OrderInfoDao;
import com.gyb.seckill.entity.OrderInfo;
import com.gyb.seckill.entity.User;
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
    private final CurrentUserService currentUserService;
    private final OrderInfoDao orderInfoDao;

    public OrderService(OrderInfoDao orderInfoDao,
                        CurrentUserService currentUserService) {
        this.orderInfoDao = orderInfoDao;
        this.currentUserService = currentUserService;
    }

    public ResponseResult getOrder(long id) {
        final User user = currentUserService.getCurrentUser();
        OrderInfo order = orderInfoDao.findByIdAndUserId(id, user.getId());
        if (order == null)
            return ResponseResult.error("该订单不存在！");
        return ResponseResult.ok(order);
    }

    public OrderInfo getOrderDetail(long id) {
        final User user = currentUserService.getCurrentUser();
        return orderInfoDao.findByIdAndUserId(id, user.getId());
    }
}
