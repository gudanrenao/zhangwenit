package com.zhangwen.learn.zhangwenit.rabbitmq.demo.producer_confirm.confirm;

import com.rabbitmq.client.MessageProperties;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.util.concurrent.TimeUnit;

/**
 * @Description 发送方确认机制，同步
 * @Author ZWen
 * @Date 2019/6/18 5:36 PM
 * @Version 1.0
 **/
public class ConfirmDemo {

    public static void main(String[] args) throws Exception {
        RabbitProducer.channel.confirmSelect();
        for (int i = 0; i < 3; i++) {
            RabbitProducer.channel.basicPublish("exchange_demo", "routingkey_demo"
                    , MessageProperties.PERSISTENT_TEXT_PLAIN, ("confirm demo " + i).getBytes());
            if (!RabbitProducer.channel.waitForConfirms()) {
                System.out.println("等待第 " + i + " 条消息确认失败并睡眠2秒");
                TimeUnit.SECONDS.sleep(2);
                System.err.println("send message failed " + i);
            } else {
                System.out.println("等待第 " + i + " 条消息确认成功并睡眠2秒");
                TimeUnit.SECONDS.sleep(2);
                System.err.println("send message success " + i);
            }
        }
    }
}