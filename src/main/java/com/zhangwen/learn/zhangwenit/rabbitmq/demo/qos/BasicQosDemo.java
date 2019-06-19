package com.zhangwen.learn.zhangwenit.rabbitmq.demo.qos;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 设置消费者负载能力(未确认消息数量)
 * @Author ZWen
 * @Date 2019/6/19 10:07 AM
 * @Version 1.0
 **/
public class BasicQosDemo {

    public static Connection connection = null;
    public static Channel channel = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            //第二种连接方式
            factory.setUri("amqp:root:root123@localhost:5672/");
            //创建连接
            connection = factory.newConnection();
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        qosUseGlobalTrueProducer();
        qosUseGlobalTrueConsumer();
    }

    public static void qosUseGlobalTrueProducer() throws Exception {
        channel.queueDeclare("qos.demo.1", false, false, false, null);
        channel.queueDeclare("qos.demo.2", false, false, false, null);
        channel.queueBind("qos.demo.1", "exchange_demo", "qos-demo-1");
        channel.queueBind("qos.demo.2", "exchange_demo", "qos-demo-2");

        //向两个队列各发送8条消息
        for (int i = 0; i < 8; i++) {
            channel.basicPublish("exchange_demo", "qos-demo-1", MessageProperties.PERSISTENT_TEXT_PLAIN, ("qos-demo-1-" + i).getBytes());
            channel.basicPublish("exchange_demo", "qos-demo-2", MessageProperties.PERSISTENT_TEXT_PLAIN, ("qos-demo-2-" + i).getBytes());
        }
    }

    /**
     * 如果设置 global为true,表示整个信道的最大未确认消息总数为prefetchCount
     * 如果设置 global为false,表示信道上的每个消费队列的最大未确认消息总数为prefetchCount
     * <p>
     * 存疑：如果既设置了global为true，又设置了global为false,那么两者都是生效的，即保证false的每条不超限，又保证true的整体不超限？？？
     * todo:但是测试显示设置了false的，true的设置会失效
     *
     * @throws Exception
     */
    public static void qosUseGlobalTrueConsumer() throws Exception {
        channel.basicQos(2, true);
        channel.basicQos(3, false);
        channel.basicConsume("qos.demo.1", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println("qos.demo.1 : tag : " + consumerTag + " message : " + new String(body));
            }
        });
        channel.basicConsume("qos.demo.2", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println("qos.demo.2 : tag : " + consumerTag + " message : " + new String(body));
            }
        });
    }
}