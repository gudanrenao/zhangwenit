package com.zhangwen.learn.zhangwenit.rabbitmq.demo.ttl;

import com.rabbitmq.client.AMQP;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitConsumer;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/17 10:55 AM
 * @Version 1.0
 **/
public class MessageTTLDemo {

    public static void main(String[] args) throws Exception {
        sendMsgWithTTL();
        consumerMsgWithOutExpiredMsg();
    }

    /**
     * 注意：这种过期方式，即使消息过期，也不会马上从队列中抹去，因为每条消息是否过期是在即将投递到消费者之前判定的。
     *
     * @throws Exception
     */
    public static void sendMsgWithTTL() throws Exception {
        RabbitProducer.channel.basicPublish("exchange_demo", "routingkey_demo", new AMQP.BasicProperties.Builder()
                .expiration("5000")
                .deliveryMode(2).build(), "ttl-message2".getBytes());
        RabbitProducer.close();
    }

    public static void consumerMsgWithOutExpiredMsg() throws Exception {
        RabbitConsumer.channel.basicConsume("queue_demo", RabbitConsumer.consumer);
//        RabbitConsumer.close();
    }
}