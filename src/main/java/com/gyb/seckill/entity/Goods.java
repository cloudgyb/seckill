package com.gyb.seckill.entity;

import lombok.*;

import java.math.BigDecimal;

/**
 * 商品实体
 *
 * @author geng
 * 2020/5/27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Goods {
    private long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private BigDecimal goodsPrice;
    private int goodsStock;
}
