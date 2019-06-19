package com.zhangwen.learn.zhangwenit.rabbitmq.demo.ttl;

import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/17 11:33 AM
 * @Version 1.0
 **/
public class QueueTTLDemo {

    public static void main(String[] args) throws Exception {
        declareQueueWithTTL();
    }

    public static void declareQueueWithTTL() throws Exception {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-expires", 10000);
        RabbitProducer.channel.queueDeclare("ttl-queue-demo", true, false, false, arguments);
        RabbitProducer.close();
    }
}