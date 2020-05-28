package com.gyb.seckill.entity;

import lombok.*;

/**
 * 秒杀订单 实体类
 *
 * @author geng
 * 2020/5/27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MiaoshaOrder {

  private long id;
  private int userId;
  private long goodsId;
  private long orderId;

}
