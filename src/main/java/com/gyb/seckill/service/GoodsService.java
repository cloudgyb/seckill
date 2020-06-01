package com.gyb.seckill.service;

import com.gyb.seckill.dao.GoodsDao;
import com.gyb.seckill.dao.MiaoshaGoodsDao;
import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geng
 * 2020/6/1
 */
@Service
public class GoodsService {
    private GoodsDao goodsDao;
    private MiaoshaGoodsDao miaoshaGoodsDao;

    @Autowired
    public GoodsService(GoodsDao goodsDao, MiaoshaGoodsDao miaoshaGoodsDao) {
        this.goodsDao = goodsDao;
        this.miaoshaGoodsDao = miaoshaGoodsDao;
    }

    public List<Goods> getAllGoods(){
        return goodsDao.findAll();
    }

    public List<MiaoshaGoodsDTO> getAllMiaoshaGoods(){
        return miaoshaGoodsDao.findAll();
    }
}
