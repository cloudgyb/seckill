package com.gyb.seckill.vo;

import com.gyb.seckill.entity.Goods;
import com.gyb.seckill.entity.OrderInfo;
import lombok.Data;

/**
 * 订单VO
 *
 * @author geng
 * 2021/1/9
 */
@Data
public class OrderDetailVO {
    private OrderInfo orderInfo;
    private Goods goods;
}
