package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 8:08 PM
 * @Version 1.0
 **/
public class ChatClient {

    private String host;
    private int port;
    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
    }

    public void connect() {
        try {
            bootstrap
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientHandler());

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            final Channel channel = channelFuture.channel();
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()){
                    UserDto userDto = new UserDto(channel.localAddress().toString(),scanner.nextLine());
                    channel.writeAndFlush(JSONObject.toJSONString(userDto));
                }
            }).start();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1", 8886).connect();
    }
}