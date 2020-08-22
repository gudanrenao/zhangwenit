package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 8:14 PM
 * @Version 1.0
 **/
public class ChatClientHandler extends ChannelInitializer<SocketChannel> {

    /**
     * This method will be called once the {@link Channel} was registered. After the method returns this instance
     * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
     *
     * @param ch the {@link Channel} which was registered.
     * @throws Exception is thrown if an error occurs. In that case it will be handled by
     *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
     *                   the {@link Channel}.
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("decodeHandler", new StringDecoder());
        ch.pipeline().addLast("encodeHandler", new StringEncoder());
        ch.pipeline().addLast("msgHandler", new ChatClientMsgInHandler());
    }
}