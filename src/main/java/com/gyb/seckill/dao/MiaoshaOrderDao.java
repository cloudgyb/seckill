package com.gyb.seckill.dao;

import com.gyb.seckill.entity.MiaoshaOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单信息 Dao
 *
 * @author geng
 * 2020/5/27
 */
@Repository
@Mapper
public interface MiaoshaOrderDao {
    List<MiaoshaOrder> findAll();

    int save(MiaoshaOrder miaoshaOrder);

    List<MiaoshaOrder> findByGoodsIdAndUserId(long goodsId, int userId);
}
