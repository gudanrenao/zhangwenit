package com.zhangwen.learn.zhangwenit.rabbitmq.demo.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @Description rpc 服务端demo
 * @Author ZWen
 * @Date 2019/8/21 11:02 AM
 * @Version 1.0
 **/
public class RpcServerDemo extends RpcServer {

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
            channel.basicQos(64);
            //声明queue
            channel.queueDeclare(queueName,true,false,false,null);

        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

    public RpcServerDemo(Channel channel, String queueName) throws IOException {
        super(channel, queueName);
    }

    /**
     * 响应
     * High-level response method. Returns an empty response by
     * default - override this (or other handleCall and handleCast
     * methods) in subclasses.
     *
     * @param requestBody
     * @param replyProperties
     */
    @Override
    public byte[] handleCall(byte[] requestBody, AMQP.BasicProperties replyProperties) {
        String request = new String(requestBody);
        String response = "response : " + new Date() + " : " + request;
        return super.handleCall(response.getBytes(), replyProperties);
    }

    public static void main(String[] args) throws IOException {
        RpcServer rpcServer = new RpcServerDemo(channel, queueName);
        rpcServer.mainloop();
    }

}