package com.zhangwen.learn.zhangwenit.rabbitmq.demo.delay;

import com.rabbitmq.client.*;
import com.zhangwen.learn.zhangwenit.rabbitmq.demo.first.RabbitProducer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 延迟队列消费测试
 * @Author ZWen
 * @Date 2019/6/17 5:07 PM
 * @Version 1.0
 **/
public class DelayQueueConsumerDemo {

    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static Connection connection = null;
    public static Channel channel = null;
    public static Consumer consumer = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            //第一种连接方式
//        factory.setHost(IP_ADDRESS);
//        factory.setPort(PORT);
//        factory.setUsername("root");
//        factory.setPassword("root123");
            //第二种连接方式
            factory.setUri("amqp:root:root123@localhost:5672/");
            //创建连接
            Address[] addresses = new Address[]{new Address(IP_ADDRESS, PORT)};
            connection = factory.newConnection(addresses);
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
            //设置客户端最多接收未被 ack 的消息的个数
            channel.basicQos(64);

            consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    System.err.println("消息延迟 ： " + (System.currentTimeMillis() - Long.valueOf(new String(body))));
                    RabbitProducer.channel.basicAck(envelope.getDeliveryTag(), false);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    //拒绝消息 requeue参数为true,表示将该消息重新加入队列，为false,表示舍弃该消息
//                    channel.basicReject(envelope.getDeliveryTag(),true);
                }
            };
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        channel.basicConsume("queue-delay-5s", consumer);
        channel.basicConsume("queue-delay-10s", consumer);
        channel.basicConsume("queue-delay-30s", consumer);
//        RabbitConsumer.channel.basicConsume("queue-delay-30s", (consumerTag, message) -> {
//            System.err.println("消息延迟 ： " + (System.currentTimeMillis() - Long.valueOf(new String(message.getBody()))));
//            RabbitProducer.channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
//        }, (CancelCallback) null);
    }
}