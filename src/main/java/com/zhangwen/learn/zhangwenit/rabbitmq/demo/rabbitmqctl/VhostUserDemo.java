package com.zhangwen.learn.zhangwenit.rabbitmq.demo.rabbitmqctl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/19 2:13 PM
 * @Version 1.0
 **/
public class VhostUserDemo {

    public static Connection connection = null;
    public static Channel channel = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            //test vhost
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setUsername("vhost_test");
            factory.setPassword("123456");
            factory.setVirtualHost("test");
            //创建连接
            connection = factory.newConnection();
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        testVHostCreateExAndQueue();
        channel.close();
        connection.close();
    }

    /**
     * 在test vhost 中创建交换器和队列
     *
     * @throws Exception
     */
    public static void testVHostCreateExAndQueue() throws Exception {
        channel.exchangeDeclare("exchange.#.test", BuiltinExchangeType.TOPIC, false, false, null);
    }
}