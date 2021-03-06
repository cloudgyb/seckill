package com.gyb.seckill.dao;

import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 秒杀商品 Dao
 *
 * @author geng
 * 2020/5/27
 */
@Repository
@Mapper
public interface MiaoshaGoodsDao {
    List<MiaoshaGoodsDTO> findAll();
    MiaoshaGoodsDTO getById(long id);
    MiaoshaGoodsDTO getByGoodsId(long goodsId);
    int decrementStock(long id);
}
