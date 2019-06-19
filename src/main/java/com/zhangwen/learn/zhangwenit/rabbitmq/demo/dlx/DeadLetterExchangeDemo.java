package com.zhangwen.learn.zhangwenit.rabbitmq.demo.dlx;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.MessageProperties;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 死信队列
 * @Author ZWen
 * @Date 2019/6/17 12:02 PM
 * @Version 1.0
 **/
public class DeadLetterExchangeDemo {

    public static void main(String[] args) throws Exception {
//        deadLetterExchangeDemo();
//        addDLXQueue();
        publish();
    }

    public static void deadLetterExchangeDemo() throws Exception {
        //声明死信交换器
        RabbitProducer.channel.exchangeDeclare("dlx-exchange-demo", BuiltinExchangeType.DIRECT);
        //为其他队列添加死信交换器
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlx-exchange-demo");
        //为这个 DLX 指定路由键，如果没有特殊指定，则使用原队列的路由键:
        arguments.put("x-dead-letter-routing-key", "dlx-routing-key");
        //给队列消息添加超时时间，为了测试死信队列
        arguments.put("x-message-ttl", 5000);
        RabbitProducer.channel.queueDeclare("queue-demo", false, false, false, arguments);
        RabbitProducer.channel.queueBind("queue-demo", "exchange_demo", "dead-demo-key");
        RabbitProducer.close();
    }

    public static void addDLXQueue() throws Exception {
        RabbitProducer.channel.queueDeclare("dlx-queue-demo", false, false, false, null);
        RabbitProducer.channel.queueBind("dlx-queue-demo", "dlx-exchange-demo", "dlx-routing-key");
        RabbitProducer.close();
    }

    public static void publish() throws Exception {
        RabbitProducer.channel.basicPublish("exchange_demo", "dead-demo-key", MessageProperties.PERSISTENT_TEXT_PLAIN, "dead-message-demo".getBytes());
        RabbitProducer.close();
    }
}