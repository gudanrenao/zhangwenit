package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 7:33 PM
 * @Version 1.0
 **/
public class ChatServer {

    private int port;
    private EventLoopGroup parentEventLoopGroup;
    private EventLoopGroup childEventLoopGroup;
    private ServerBootstrap bootstrap;

    public ChatServer(int port) {
        this.port = port;
        parentEventLoopGroup = new NioEventLoopGroup(1);
        childEventLoopGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
    }

    public void open() {
        bootstrap
                .group(parentEventLoopGroup, childEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChatServerChildInitHandler());

        try {
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentEventLoopGroup.shutdownGracefully();
            childEventLoopGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new ChatServer(8886).open();
    }
}