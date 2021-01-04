package com.gyb.seckill.service;

import com.gyb.seckill.dao.MiaoshaGoodsDao;
import com.gyb.seckill.dao.MiaoshaOrderDao;
import com.gyb.seckill.dao.OrderInfoDao;
import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.entity.MiaoshaOrder;
import com.gyb.seckill.entity.OrderInfo;
import com.gyb.seckill.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 秒杀服务
 *
 * @author geng
 * 2021/1/4
 */
@Slf4j
@Service
public class SecondsKillService {
    private final MiaoshaGoodsDao miaoshaGoodsDao;
    private final MiaoshaOrderDao miaoshaOrderDao;
    private final OrderInfoDao orderInfoDao;

    private final TransactionTemplate transactionTemplate;

    public SecondsKillService(MiaoshaGoodsDao miaoshaGoodsDao,
                              MiaoshaOrderDao miaoshaOrderDao,
                              OrderInfoDao orderInfoDao,
                              TransactionTemplate transactionTemplate) {
        this.miaoshaGoodsDao = miaoshaGoodsDao;
        this.miaoshaOrderDao = miaoshaOrderDao;
        this.orderInfoDao = orderInfoDao;
        this.transactionTemplate = transactionTemplate;
    }

    public ResponseResult secondsKill(long goodsId,int userId) {
        final MiaoshaGoodsDTO secondsGoods = miaoshaGoodsDao.getByGoodsId(goodsId);
        if(secondsGoods == null){
            return ResponseResult.error("该商品暂时无法秒杀！");
        }
        if(secondsGoods.getStartDate().getTime() > System.currentTimeMillis()){
            return ResponseResult.error("秒杀还没开始呢！");
        }
        if(secondsGoods.getEndDate().getTime() < System.currentTimeMillis()){
            return ResponseResult.error("秒杀已经结束了！");
        }
        if(secondsGoods.getStockCount() <= 0){
            return ResponseResult.error("来晚了，商品已被抢完了！");
        }
        List<MiaoshaOrder> miaoshaOrderOld = miaoshaOrderDao.findByGoodsIdAndUserId(goodsId,userId);
        if(miaoshaOrderOld != null && !miaoshaOrderOld.isEmpty()){
            return ResponseResult.error("你已经秒杀过该商品了，别贪心！");
        }
        final OrderInfo order = transactionTemplate.execute(transactionStatus -> {
            //减库存
            final int i = miaoshaGoodsDao.decrementStock(secondsGoods.getId());
            log.info(String.format("秒杀商品id：%d库存减%d", secondsGoods.getId(), i));
            final OrderInfo orderInfo = new OrderInfo();
            orderInfo.setGoodsId(secondsGoods.getGoodsId());
            orderInfo.setDeliveryAddrId(12);
            orderInfo.setGoodsCount(1);
            orderInfo.setOrderChannel(1);
            orderInfo.setGoodsName(secondsGoods.getGoodsName());
            orderInfo.setGoodsPrice(secondsGoods.getMiaoshaPrice());
            orderInfo.setStatus(0);
            orderInfo.setCreateDate(new Timestamp(new Date().getTime()));
            orderInfo.setUserId(userId);
            final int n = orderInfoDao.save(orderInfo);
            if (n > 0)
                log.info(String.format("生成新订单id:%d", orderInfo.getId()));
            final MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
            miaoshaOrder.setOrderId(orderInfo.getId());
            miaoshaOrder.setGoodsId(secondsGoods.getGoodsId());
            miaoshaOrder.setUserId(userId);
            final int save = miaoshaOrderDao.save(miaoshaOrder);
            if (save > 0)
                log.info(String.format("生成新的秒杀订单id:%d", miaoshaOrder.getId()));
            return orderInfo;
        });
        return ResponseResult.ok(order);
    }
}
