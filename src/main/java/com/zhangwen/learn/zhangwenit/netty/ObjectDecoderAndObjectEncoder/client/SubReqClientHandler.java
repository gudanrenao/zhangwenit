package com.zhangwen.learn.zhangwenit.netty.ObjectDecoderAndObjectEncoder.client;

import com.zhangwen.learn.zhangwenit.netty.ObjectDecoderAndObjectEncoder.server.SubscribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/27 1:46 PM
 * @Version 1.0
 **/
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(req(i));
        }
        ctx.flush();
    }

    private SubscribeReq req(int id) {
        SubscribeReq req = new SubscribeReq();
        req.setId(id).setName("testName" + id);
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive from server msg is : " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}