package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 7:50 PM
 * @Version 1.0
 **/
public class ChatServerChildInitHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("用户【" + ch.remoteAddress() + "】上线！");
        ch.pipeline().addLast("decodeHandler" , new StringDecoder());
        ch.pipeline().addLast("encodeHandler" , new StringEncoder());
        //添加心跳事件
        ch.pipeline().addLast("heartListener" , new IdleStateHandler(5,10,15));
        ch.pipeline().addLast(new MsgInHandler());
    }
}