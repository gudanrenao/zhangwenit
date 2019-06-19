package com.zhangwen.learn.zhangwenit.rabbitmq.demo.producer_confirm.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description 异步确认，可提高效率，需维护状态
 * @Author ZWen
 * @Date 2019/6/18 6:26 PM
 * @Version 1.0
 **/
public class AsyncConfirmDemo {

    public static Connection connection = null;
    public static Channel channel = null;

    private static final SortedSet<Long> unconfirmedSet = Collections.synchronizedSortedSet(new TreeSet<>());

    static {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            //第二种连接方式
            factory.setUri("amqp:root:root123@localhost:5672/");
            //创建连接
            connection = factory.newConnection();
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
            //添加确认监听器
            addConfirmListener();
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加异步确认监听器
     */
    public static void addConfirmListener() {
        channel.addConfirmListener((deliveryTag, multiple) -> {
            //确认收到处理逻辑
            System.err.println("send message success " + deliveryTag + " multiple is " + multiple);
            //如果为true,说明是一次确认了多条消息，消息Tag为本次确认消息的最后一条消息id
            if (multiple) {
                unconfirmedSet.headSet(deliveryTag + 1).clear();
            } else {
                unconfirmedSet.remove(deliveryTag);
            }

        }, (deliveryTag, multiple) -> {
            //negative ack 处理逻辑
            System.err.println("send message failed " + deliveryTag + " multiple is " + multiple);
            if (multiple) {
                unconfirmedSet.headSet(deliveryTag + 1).clear();
            } else {
                unconfirmedSet.remove(deliveryTag);
            }
            //添加自己的处理消息重发的场景代码
        });
    }

    public static void main(String[] args) throws Exception {
        channel.confirmSelect();
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            channel.basicPublish("exchange_demo", "routingkey_demo"
                    , MessageProperties.PERSISTENT_TEXT_PLAIN, ("confirm demo " + i).getBytes());
        }
    }
}