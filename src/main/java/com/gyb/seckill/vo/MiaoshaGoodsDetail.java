package com.gyb.seckill.vo;

import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 秒杀商品详情页model
 *
 * @author geng
 * 2020/6/23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaGoodsDetail {
    private MiaoshaGoodsDTO goodsDetail;
    private int miaoshaStatus;
    private long remainSeconds;
}
