package com.zhangwen.learn.zhangwenit.basic.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @Description 客户端
 * @Author ZWen
 * @Date 2020/6/5 11:26 PM
 * @Version 1.0
 **/
public class ClientSocketChannel1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();

        //设置非阻塞模式
        socketChannel.configureBlocking(false);

        //服务端地址信息
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //连接
        if(!socketChannel.connect(socketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他事情");
            }
        }
        //连接成功，发送数据
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello,world!".getBytes());
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}