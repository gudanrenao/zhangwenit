package com.zhangwen.learn.zhangwenit.rabbitmq.demo.first;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 生产者客户端代码
 * @Author ZWen
 * @Date 2019/6/12 6:12 PM
 * @Version 1.0
 **/
public class RabbitProducer {

    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static Connection connection = null;
    public static Channel channel = null;

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
            connection = factory.newConnection();
            //在connection上添加shutdownListener
            addShutdownListener();
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
            //在channel上添加ReturnListener
            addReturnListener();
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
//        firstDemo();
//        exchangeToExchangeDemo();
//        queueBind();
//        publishNoQueueMsg();
        queueDelete("queue-demo");
    }

    public static void exchangeToExchangeDemo() throws Exception {
        channel.exchangeDeclare("source", BuiltinExchangeType.DIRECT, false, true, null);
        channel.exchangeDeclare("destination", BuiltinExchangeType.FANOUT, false, true, null);
        channel.exchangeBind("destination", "source", "exKey");
        channel.queueDeclare("queue", false, false, true, null);
        channel.queueBind("queue", "destination", "aaa");
        channel.basicPublish("source", "exKey", null, "exchangeToExchangeDemo".getBytes());

        close();
    }

    public static void queueBind() throws Exception {
        channel.queueDeclare("order.demo", true, false, false, null);
        channel.queueBind("order.demo", EXCHANGE_NAME, "order");
        close();
    }

    /**
     * 发送消息，如果没有路由到队列，那么返回给生产值，需要设置mandatory为true
     *
     * @throws Exception
     */
    public static void publishNoQueueMsg() throws Exception {
        String message = "return msg";
        channel.basicPublish(EXCHANGE_NAME, "", true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//        TimeUnit.SECONDS.sleep(500000);
//        close();
    }

    public static void firstDemo() throws Exception {
        //创建一个 type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        //排他队列仅对首次声明它的连接可见，并在连接断开时自动删除
        //排他队列是基于连接( Connection) 可见的，同 一个连接的不同信道 (Channel) 是可以同时访问同一连接创建的排他队列;
        //这种排他队列 适用于一个客户端同时发送和读取消息的应用场景。
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        //发送一条持久化的消息: hello world !
        String message = "Hello World !";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        close();
        //下面这个队列(自动删除，非持久化)，只对当前应用的同一个Connection层面可用，同一个Connection的不同的Channel可
        //公用，并且也会在应用连接断开时自动删除。
//        channel.queueDeclare();

    }

    /**
     * 当 Connection 或者 Channel 的状 态转变为 Closed 的时候会调用 ShutdownListener
     */
    public static void addShutdownListener() {
        connection.addShutdownListener(cause -> {
            System.err.println("connection is shutdown");
            System.err.println(cause);
        });
    }

    /**
     * 添加监听器，两种情况消息会返回
     * 1.如果消息没有路由到队列中，且publish时设置mandatory为true
     * 2.如果publish时设置immediate为true,且交换机将消息路由到队列中后发现队列上不存在任何消费者，那么这条消息将不会存入队列，
     * 当匹配的所有队列都没有消费者时，那么该消息会通过Basic.Return返回到生产者
     * <p>
     * 概括来说, mandatory 参数告诉服务器至少将该消息路由到一个队列中， 否则将消息返回给生产者。 immediate参数告诉服务器，
     * 如果该消息关联的队列上有消费者,则立刻投递: 如果所有匹配的队列上都没有消费者，则直接将消息返还给生产者 ，不用将消息存入队列而等待消费者了。
     * <p>
     * RabbitMQ 3.0版本开始去掉了对 immediate 参数的支持，对此 RabbitMQ 官方解释是: immediate 参数会影响镜像队列的性能 ， 增加了代码复杂性 ，
     * 建议采用 TTL 和 DLX 的方法替代
     */
    public static void addReturnListener() {
        channel.addReturnListener(msg -> {
            System.err.println("publish fail ... ");
            System.err.println(new String((msg.getBody())));

        });
    }

    public static void close() throws Exception {
        //关闭资源
        channel.close();
        connection.close();
    }

    public static void queueDelete(String queueName) throws Exception {
        channel.queueDelete(queueName);
        close();
    }
}