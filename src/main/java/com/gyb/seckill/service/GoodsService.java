package com.gyb.seckill.service;

import com.gyb.seckill.dao.GoodsDao;
import com.gyb.seckill.dao.MiaoshaGoodsDao;
import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.entity.Goods;
import com.gyb.seckill.vo.MiaoshaGoodsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    public List<Goods> getAllGoods(){
        return goodsDao.findAll();
    }

    public List<MiaoshaGoodsDTO> getAllMiaoshaGoods(){
        return miaoshaGoodsDao.findAll();
    }

    public MiaoshaGoodsDetail getMiaoshaGoodsDetail(long id){
        MiaoshaGoodsDTO miaoshaGoodsDTO = miaoshaGoodsDao.getById(id);
        MiaoshaGoodsDetail miaoshaGoodsDetail = new MiaoshaGoodsDetail();
        miaoshaGoodsDetail.setGoodsDetail(miaoshaGoodsDTO);
        long startTime = miaoshaGoodsDTO.getStartDate().getTime();
        long endTime = miaoshaGoodsDTO.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus;
        long remainSeconds = 0; //距离秒杀开始或者结束还剩多少秒
        if(startTime > now){ //秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = startTime - now;
        }else if(startTime < now && now < endTime){ //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = endTime - now;
        }else{ //秒杀结束
            miaoshaStatus = 2;
        }
        remainSeconds = remainSeconds/1000;
        miaoshaGoodsDetail.setMiaoshaStatus(miaoshaStatus);
        miaoshaGoodsDetail.setRemainSeconds(remainSeconds);
        return miaoshaGoodsDetail;
    }
}