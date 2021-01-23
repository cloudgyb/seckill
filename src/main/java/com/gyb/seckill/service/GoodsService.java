package com.gyb.seckill.service;

import com.gyb.seckill.config.cache.Cacheable;
import com.gyb.seckill.dao.GoodsDao;
import com.gyb.seckill.dao.MiaoshaGoodsDao;
import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务类
 *
 * @author geng
 * 2020/6/1
 */
@Service
public class GoodsService {
    private final GoodsDao goodsDao;
    private final MiaoshaGoodsDao miaoshaGoodsDao;

    @Autowired
    public GoodsService(GoodsDao goodsDao, MiaoshaGoodsDao miaoshaGoodsDao) {
        this.goodsDao = goodsDao;
        this.miaoshaGoodsDao = miaoshaGoodsDao;
    }

    public Goods getGoods(long goodsId) {
        return goodsDao.findById(goodsId);
    }

    @Cacheable(cacheName = "goods:all", expireTime = 60)
    public List<MiaoshaGoodsDTO> getAllMiaoshaGoods() {
        return miaoshaGoodsDao.findAll();
    }

    @Cacheable(cacheName = "goods:detail", key = "id", expireTime = 60)
    public MiaoshaGoodsDTO getMiaoshaGoodsDetail(long id) {
        return miaoshaGoodsDao.getById(id);
    }
}
