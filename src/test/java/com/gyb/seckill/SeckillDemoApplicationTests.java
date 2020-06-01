package com.gyb.seckill;

import com.gyb.seckill.dao.GoodsDao;
import com.gyb.seckill.dao.MiaoshaGoodsDao;
import com.gyb.seckill.dao.MiaoshaOrderDao;
import com.gyb.seckill.dao.OrderInfoDao;
import com.gyb.seckill.dto.MiaoshaGoodsDTO;
import com.gyb.seckill.entity.Goods;
import com.gyb.seckill.entity.MiaoshaGoods;
import com.gyb.seckill.entity.MiaoshaOrder;
import com.gyb.seckill.entity.OrderInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SeckillDemoApplicationTests {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private MiaoshaGoodsDao miaoshaGoodsDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private MiaoshaOrderDao miaoshaOrderDao;
    @Test
    void contextLoads() {
        List<Goods> all = goodsDao.findAll();
        for (Goods goods : all) {
            System.out.println(goods);
        }
    }

    @Test
    void findAllMiaoshaGoods(){
        List<MiaoshaGoodsDTO> all = miaoshaGoodsDao.findAll();
        for (MiaoshaGoodsDTO miaoshaGoods : all) {
            System.out.println(miaoshaGoods);
        }
    }

    @Test
    void findAllOrderInfo(){
        List<OrderInfo> all = orderInfoDao.findAll();
        for (OrderInfo orderInfo : all) {
            System.out.println(orderInfo);
        }
    }


    @Test
    void findAllMiaoshaOrder() {
        List<MiaoshaOrder> all = miaoshaOrderDao.findAll();
        for (MiaoshaOrder miaoshaOrder : all) {
            System.out.println(miaoshaOrder);
        }
    }
}
