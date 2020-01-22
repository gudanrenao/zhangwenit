package com.zhangwen.learn.zhangwenit.basic.java.nio.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 独立线程 负责轮询多路复用器selector
 * @Author ZWen
 * @Date 2020/1/22 3:02 PM
 * @Version 1.0
 **/
public class MultiplexerServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     *
     * @param port
     */
    public MultiplexerServer(int port) {
        try {
            //创建多路复用器
            selector = Selector.open();
            //打开服务端socket连接
            serverSocketChannel = ServerSocketChannel.open();
            //绑定监听端口，设置为非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", port), 1024);
            serverSocketChannel.configureBlocking(false);
            //将ServerSocketChannel注册到多路复用器上，监听Accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //每隔1秒循环一次
                selector.select(1000);
                //返回就需的channel的selectionKey的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //遍历，处理
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (selectionKeyIterator.hasNext()) {
                    key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                //处理新接入的请求消息
                //accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                //读取数据
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    //flip:将当前的limit设置为position,将position设置为0，用于后续对缓冲区的读取操作
                    readBuffer.flip();
                    //根据缓冲区可读的字节数创建直接数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //将缓冲区可读的字节复制到字节数组中
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("server receive body is : " + body);
                    String responseBody = "收到client【" + body + "】 response is : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    doWrite(sc, responseBody);
                } else if (readBytes < 0) {
                    //对端链路管理
                    key.channel();
                    sc.close();
                } else {
                    //读取到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String body) throws IOException {
        if (StringUtils.isNotEmpty(body)) {
            byte[] bytes = body.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //将要响应的字节复制到缓冲区中
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

    private void stop() {
        this.stop = true;
    }
}