package com.zhangwen.learn.zhangwenit.netty.second;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/8 12:41 AM
 * @Version 1.0
 **/
public class Server {

    public static void main(String[] args) {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(mainGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("客户端连接成功：" + ch.pipeline().channel().remoteAddress() + ", thread is " + Thread.currentThread().getName());
                        ch.pipeline().addLast(new SocketChannelHandler());
                    }
                });

        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.bind(6668).sync();
            System.out.println("服务端启动成功");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            mainGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    private static class SocketChannelHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println("客户端地址：" + ctx.channel().remoteAddress() + ", 接收到信息 ： " + byteBuf.toString(CharsetUtil.UTF_8) + ", thread is " + Thread.currentThread().getName());

            //这是一个耗时任务的异步响应
            ctx.channel().eventLoop().execute(() -> {
                System.out.println("睡 5秒 后响应你 , Thread: " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("这是5秒后的耗时任务响应，" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).getBytes()));
            });

            //这是一个定时任务，接收到消息后，10秒后给通知 这里计算延迟比较的时候添加任务时和任务执行时的时间间隔
            //如果之前的队列已经消耗掉部分时间，那么这里执行的时候，也算作延迟时间的一部分
            //如果之前处理其他任务的时候超过了本延迟任务的时候，那么任务从延迟队列取出来时会立即执行
            ctx.channel().eventLoop().schedule(() -> {
                System.out.println("定时延迟 10秒 后响应你 , Thread: " + Thread.currentThread().getName());
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("这是10秒后的定时响应，" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).getBytes()));
            }, 3, TimeUnit.SECONDS);
            ctx.channel().eventLoop().schedule(() -> {
                System.out.println("再次定时延迟 10秒 后响应你 , Thread: " + Thread.currentThread().getName());
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("这是10秒后的定时响应，" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).getBytes()));
            }, 4, TimeUnit.SECONDS);

            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("收到，这是服务端响应哦，" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).getBytes()));

        }


        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}