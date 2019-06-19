package com.zhangwen.learn.zhangwenit.rabbitmq.demo.first;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description 消费者客户端代码
 * @Author ZWen
 * @Date 2019/6/13 10:02 AM
 * @Version 1.0
 **/
public class RabbitConsumer {

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
                    System.out.println("receive message: " + new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        firstDemo();
//        exchangeToExchangeDemo();
    }

    public static void exchangeToExchangeDemo() throws Exception {
        channel.basicConsume("queue", consumer);
        close();
    }

    public static void firstDemo() throws Exception {
        channel.basicConsume(QUEUE_NAME, consumer);
        close();
    }


    public static void close() throws Exception {
        //等待回调函数执行完毕之后 ， 关闭资源
        TimeUnit.SECONDS.sleep(500000);
        channel.close();
        connection.close();
    }
}