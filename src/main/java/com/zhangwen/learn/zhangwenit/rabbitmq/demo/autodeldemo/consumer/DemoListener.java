package com.zhangwen.learn.zhangwenit.rabbitmq.demo.autodeldemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/8/15 3:07 PM
 * @Version 1.0
 **/
@Component
public class DemoListener {

    /**
     * 消息消费
     */
    @RabbitListener(queues = {"queue_demo"})
    public void received(byte[] msg) {
        String request = new String(msg);
        System.out.println("【queue_demo】 received message:" + request);
    }
}