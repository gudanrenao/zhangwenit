package com.zhangwen.learn.zhangwenit.rabbitmq.demo.ttl;

import com.rabbitmq.client.MessageProperties;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/17 10:31 AM
 * @Version 1.0
 **/
public class MessageTTLByQueuePropertiesDemo {

    public static void main(String[] args) throws Exception {
        publishMsgToTTLQueue();
    }

    /**
     * 声明有过期时间的队列
     *
     * @throws Exception
     */
    public static void declareMsgTTLByQueue() throws Exception {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 6000);
        RabbitProducer.channel.queueDeclare("ttl-msg-demo", false, false, false, arguments);
        RabbitProducer.close();
    }

    /**
     * 向TTL队列发送消息，模拟不消费
     *
     * @throws Exception
     */
    public static void publishMsgToTTLQueue() throws Exception {
        RabbitProducer.channel.queueBind("ttl-msg-demo","exchange_demo","ttl-demo-key");
        RabbitProducer.channel.basicPublish("exchange_demo", "ttl-demo-key", MessageProperties.PERSISTENT_TEXT_PLAIN, "ttl-message".getBytes());
        RabbitProducer.close();
    }
}