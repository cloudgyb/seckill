package com.gyb.seckill;

import com.gyb.seckill.service.mq.MyMQSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author geng
 * 2021/1/14
 */
@SpringBootTest
public class MQTest {
    @Autowired
    private MyMQSender sender;

    @Test
    public void test(){
        sender.send("hello world");
    }
}
