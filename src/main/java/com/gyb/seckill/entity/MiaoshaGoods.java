package com.gyb.seckill.entity;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 秒杀商品 实体类
 *
 * @author geng
 * 2020/5/27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MiaoshaGoods {
    private long id;
    private long goodsId;
    private BigDecimal miaoshaPrice;
    private int stockCount;
    private Timestamp startDate;
    private Timestamp endDate;
}
