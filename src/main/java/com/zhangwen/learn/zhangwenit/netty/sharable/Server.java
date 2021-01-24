package com.zhangwen.learn.zhangwenit.netty.sharable;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

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

        final SocketChannelHandler sharableHandler = new SocketChannelHandler();
        final SocketOutChannelHandler outChannelHandler = new SocketOutChannelHandler();

        bootstrap.group(mainGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("客户端连接成功：" + ch.pipeline().channel().remoteAddress() + ", thread is " + Thread.currentThread().getName());
                        ch.pipeline().addLast(sharableHandler);
                        ch.pipeline().addLast(outChannelHandler);
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

    @ChannelHandler.Sharable
    private static class SocketChannelHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println("客户端地址：" + ctx.channel().remoteAddress() + ", 接收到信息 ： " + byteBuf.toString(CharsetUtil.UTF_8) + ", thread is " + Thread.currentThread().getName());

            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("收到，这是服务端响应哦，" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n").getBytes()));

//            ctx.fireChannelRead(msg);
            ReferenceCountUtil.release(msg);
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


    @ChannelHandler.Sharable
    private static class SocketOutChannelHandler extends ChannelOutboundHandlerAdapter {

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("输出数据给客户端。。。。。。。。。。。。。。。。。。。");
            super.write(ctx, msg, promise);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}