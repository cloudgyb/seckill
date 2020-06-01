package com.gyb.seckill.entity;


import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单信息 实体类
 *
 * @author geng
 * 2020/5/27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfo {
  private long id;
  private int userId;
  private long goodsId;
  private int deliveryAddrId;
  private String goodsName;
  private int goodsCount;
  private BigDecimal goodsPrice;
  private int orderChannel;
  //0 未支付，1已支付，2已发货，3已收货，4已退款，5已完成
  private int status;
  private Timestamp createDate;
  private Timestamp payDate;
}
