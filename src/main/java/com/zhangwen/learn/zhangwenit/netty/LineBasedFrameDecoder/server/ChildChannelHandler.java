package com.zhangwen.learn.zhangwenit.netty.LineBasedFrameDecoder.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Description 使用 LineBasedFrameDecoder 和 StringnDecoder 处理 粘包 问题
 * @Author ZWen
 * @Date 2020/1/25 12:49 PM
 * @Version 1.0
 **/
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ServerHandler());
    }
}