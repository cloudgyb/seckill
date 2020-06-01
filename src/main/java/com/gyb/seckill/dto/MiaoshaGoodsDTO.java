package com.gyb.seckill.dto;

import com.gyb.seckill.entity.Goods;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author geng
 * 2020/6/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MiaoshaGoodsDTO extends Goods {
    private long id;
    private long goodsId;
    private BigDecimal miaoshaPrice;
    private int stockCount;
    private Timestamp startDate;
    private Timestamp endDate;
}
