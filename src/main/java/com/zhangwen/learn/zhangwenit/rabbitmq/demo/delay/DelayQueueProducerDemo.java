package com.zhangwen.learn.zhangwenit.rabbitmq.demo.delay;

import com.rabbitmq.client.MessageProperties;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.concurrent.TimeUnit;

/**
 * @Description 延迟消息发送测试
 * @Author ZWen
 * @Date 2019/6/17 5:16 PM
 * @Version 1.0
 **/
public class DelayQueueProducerDemo {

    public static void main(String[] args) throws Exception {
        String[] routingkeys = new String[]{"queue-delay-5s", "queue-delay-10s", "queue-delay-30s"};
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            RabbitProducer.channel.basicPublish("delay-exchange-demo", routingkeys[i % 3], MessageProperties.PERSISTENT_TEXT_PLAIN, String.valueOf(System.currentTimeMillis()).getBytes());
        }
    }
}