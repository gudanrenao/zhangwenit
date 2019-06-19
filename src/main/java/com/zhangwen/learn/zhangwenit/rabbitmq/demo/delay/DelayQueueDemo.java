package com.zhangwen.learn.zhangwenit.rabbitmq.demo.delay;

import com.rabbitmq.client.BuiltinExchangeType;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 延迟队列，使用TTL和DLX实现
 * @Author ZWen
 * @Date 2019/6/17 4:53 PM
 * @Version 1.0
 **/
public class DelayQueueDemo {

    public static void main(String[] args) throws Exception {
        declare();
    }

    public static void declare() throws Exception {
        //声明一个死信交换器
        RabbitProducer.channel.exchangeDeclare("delay-dlx-demo", BuiltinExchangeType.DIRECT, false, false, null);
        //绑定三个队列到死信交换器上，三个队列分别和下面的设置过期时间的三个队列的routing-key保持一致，这样在绑定死信队列时不需要重新设置routing key 复用即可
        RabbitProducer.channel.queueDeclare("queue-delay-5s", false, false, false, null);
        RabbitProducer.channel.queueBind("queue-delay-5s", "delay-dlx-demo", "queue-delay-5s");
        RabbitProducer.channel.queueDeclare("queue-delay-10s", false, false, false, null);
        RabbitProducer.channel.queueBind("queue-delay-10s", "delay-dlx-demo", "queue-delay-10s");
        RabbitProducer.channel.queueDeclare("queue-delay-30s", false, false, false, null);
        RabbitProducer.channel.queueBind("queue-delay-30s", "delay-dlx-demo", "queue-delay-30s");
        //声明一个direct类型交换器
        RabbitProducer.channel.exchangeDeclare("delay-exchange-demo", BuiltinExchangeType.DIRECT, false, false, null);
        //声明三个队列，每个队列对应声明一个死信队列，每个队列设置不同的过期时间
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 5000);
        arguments.put("x-dead-letter-exchange", "delay-dlx-demo");
        RabbitProducer.channel.queueDeclare("queue-5s", false, false, false, arguments);
        RabbitProducer.channel.queueBind("queue-5s", "delay-exchange-demo", "queue-delay-5s");
        arguments.put("x-message-ttl", 10000);
        RabbitProducer.channel.queueDeclare("queue-10s", false, false, false, arguments);
        RabbitProducer.channel.queueBind("queue-10s", "delay-exchange-demo", "queue-delay-10s");
        arguments.put("x-message-ttl", 30000);
        RabbitProducer.channel.queueDeclare("queue-30s", false, false, false, arguments);
        RabbitProducer.channel.queueBind("queue-30s", "delay-exchange-demo", "queue-delay-30s");
        RabbitProducer.close();
    }
}
