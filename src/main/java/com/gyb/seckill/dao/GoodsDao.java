package com.gyb.seckill.dao;

import com.gyb.seckill.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品 Dao
 *
 * @author geng
 * 2020/5/27
 */
@Repository
@Mapper
public interface GoodsDao {
    List<Goods> findAll();
}
