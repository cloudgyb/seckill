package com.gyb.seckill.dao;

import com.gyb.seckill.entity.OrderInfo;
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
public interface OrderInfoDao {
    List<OrderInfo> findAll();
    OrderInfo findById(long id);

    OrderInfo findByIdAndUserId(long id, int userId);

    int save(OrderInfo orderInfo);
}
