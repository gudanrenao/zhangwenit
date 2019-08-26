package com.zhangwen.learn.zhangwenit.rabbitmq.demo.rpc;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @Description todo:rpc客户端
 * @Author ZWen
 * @Date 2019/8/21 11:21 AM
 * @Version 1.0
 **/
public class RpcClientDemo extends RpcClient {

    private static String repayToQueue = null;
    static Channel channel = null;
    static final String queueName = "demo_rpc_queue";

    static {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp:guest:guest@localhost:5672/");
            //创建连接
            Address[] addresses = new Address[]{new Address("localhost", 5672)};
            Connection connection = factory.newConnection(addresses);
            //创建信道      多线程间共享channel实例是非线程安全的
            channel = connection.createChannel();
            //设置客户端最多接收未被 ack 的消息的个数
            channel.basicQos(8);
            //设置响应队列名称
            repayToQueue = channel.queueDeclare("", false, false, true, null).getQueue();

        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Construct a {@link RpcClient} with the passed-in {@link RpcClientParams}.
     *
     * @param params
     * @throws IOException
     * @see RpcClientParams
     * @since 5.6.0
     */
    public RpcClientDemo(RpcClientParams params) throws IOException {
        super(params);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        RpcClientParams params = new RpcClientParams().timeout(1000);
        params.channel(RpcServerDemo.channel).replyTo(repayToQueue).exchange("").routingKey(queueName);
        RpcClientDemo rpcClient = new RpcClientDemo(params);
        String request = "hello";
//        Response response = rpcClient.stringCall(request);
        String response = rpcClient.stringCall(request);

        System.out.println(JSON.toJSONString(response));
    }
}