package com.zhangwen.learn.zhangwenit.netty.first.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/25 12:49 PM
 * @Version 1.0
 **/
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ServerHandler());
    }
}