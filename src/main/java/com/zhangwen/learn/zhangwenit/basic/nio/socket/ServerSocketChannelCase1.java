package com.zhangwen.learn.zhangwenit.basic.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/5 9:51 AM
 * @Version 1.0
 **/
public class ServerSocketChannelCase1 {

    public static void main(String[] args) throws IOException {

        //SPI SelectorProvider创建
//        final ServerSocketChannel serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();
//        final Selector selector = SelectorProvider.provider().openSelector();

        //创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //注册一个Selector
        Selector selector = Selector.open();
        //将ServerSocketChannel的accept事件注册到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            while (selector.select(1000) == 0) {
                System.out.println("服务器等待了一秒，无连接");

            }
            //获取事件
            //获取SelectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //迭代keys，处理
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();

                //判断事件类型
                if (selectionKey.isAcceptable()) {
                    //获取客户端Channel,并设置buffer
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，socketChannel is " + socketChannel);
                    //设置非阻塞模式
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //将SocketChannel的read事件注册到Selector
                    socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                }
                if (selectionKey.isReadable()) {
                    //客户端read事件
                    //获取Channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    System.out.println("接收到客户端发送信息 socketChannel is " + socketChannel);
                    //获取buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    //将数据读取到buffer
                    socketChannel.read(buffer);
                    //打印
                    System.out.println("Thread:" + Thread.currentThread().getName() + ", content:" + new String(buffer.array()));
                }

                //移除SelectionKey 防止重复处理 todo:是否有必要
                keyIterator.remove();
            }

        }
    }
}