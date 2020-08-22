package com.zhangwen.learn.zhangwenit.basic.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Description 客户端
 * @Author ZWen
 * @Date 2020/6/6 11:07 AM
 * @Version 1.0
 **/
public class ClientChatCase {

    private Selector selector;
    private SocketChannel socketChannel;

    public ClientChatCase() {
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6668));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端连接成功。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientChatCase clientChatCase = new ClientChatCase();
        //启动一个线程处理接收
        new Thread(() -> {
            while (true) {
                clientChatCase.acceptMsg();
            }
        }).start();

        //发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            clientChatCase.send(scanner.nextLine());
        }
    }

    /**
     * 接收其他客户端发送过来的信息
     */
    private void acceptMsg() {
        try {
            int select = selector.select();
            if (select > 0) {
                System.out.println("select > 0");
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                        StringBuilder sb = new StringBuilder();
                        while (channel.read(byteBuffer) > 0) {
                            sb.append(new String(byteBuffer.array()));
                            byteBuffer.clear();
                        }
                        System.out.println("接收到消息：" + sb);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String msg) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        try {
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            System.err.println("客户端发送消息异常");
            e.printStackTrace();
        }
    }
}