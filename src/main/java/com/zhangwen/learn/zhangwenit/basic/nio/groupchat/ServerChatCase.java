package com.zhangwen.learn.zhangwenit.basic.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 服务端
 * @Author ZWen
 * @Date 2020/6/6 10:24 AM
 * @Version 1.0
 **/
public class ServerChatCase {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public ServerChatCase() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            int PORT = 6668;
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            System.out.println("服务端启动完毕。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerChatCase serverChatCase = new ServerChatCase();
        serverChatCase.listen();
    }

    /**
     * 监听事件
     */
    private void listen() {
        while (true) {
            try {
                int select = selector.select(3000);
                if (select > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            System.out.println("客户端连接成功。。client id is " + socketChannel);
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            //处理客户端发来的消息
                            read(selectionKey);
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        try {
            StringBuilder sb = new StringBuilder();
            while (socketChannel.read(byteBuffer) > 0) {
                sb.append(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
            if (sb.length() > 0) {
                System.out.println("接收到客户端消息：" + sb);
                //转发到其他客户端
                sendMsgToOtherClients(sb.toString(), socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //客户端断开连接
            try {
                System.err.println("客户端下线了。。。client address is " + socketChannel.getRemoteAddress());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendMsgToOtherClients(String msg, SocketChannel socketChannel) {
        Set<SelectionKey> selectionKeys = selector.keys();
        System.out.println("目前注册到selector上的channel共 " + selectionKeys.size() + " 个");
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            SelectableChannel otherChannel = selectionKey.channel();
            //必须是SocketChannel 且不是发送者
            if (otherChannel instanceof SocketChannel && otherChannel != socketChannel) {
                SocketChannel dest = (SocketChannel) otherChannel;
                try {
                    dest.write(ByteBuffer.wrap(msg.getBytes()));
                } catch (IOException e) {
                    try {
                        System.err.println("转发消息失败，接收客户端id is " + dest.getRemoteAddress());
                    } catch (IOException e1) {
                        System.out.println("getRemoteAddress error");
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}