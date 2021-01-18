package com.gyb.seckill.service.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * 2021/1/14
 */
@Slf4j
@Service
public class MyMQSender {
    private final AmqpTemplate amqpTemplate;

    public MyMQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String mess){
        log.info("发送消息："+mess);
        amqpTemplate.convertAndSend("queue",mess);
    }
}
