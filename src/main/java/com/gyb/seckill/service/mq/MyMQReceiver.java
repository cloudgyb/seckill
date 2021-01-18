package com.gyb.seckill.service.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * 2021/1/14
 */
@Slf4j
@Service
public class MyMQReceiver {

    @RabbitListener(queues = "queue")
    public void receive(String mess){
        log.info("接收到消息："+mess);
    }
}
