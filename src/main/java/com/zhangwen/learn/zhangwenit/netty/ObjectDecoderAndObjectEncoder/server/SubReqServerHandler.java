package com.zhangwen.learn.zhangwenit.netty.ObjectDecoderAndObjectEncoder.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/27 1:24 PM
 * @Version 1.0
 **/
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        System.out.println("server receive msg is : " + req);

        ctx.writeAndFlush(resp(req.getId()));
    }

    private SubscribeResp resp(int id){
        SubscribeResp resp = new SubscribeResp();
        resp.setId(id).setStatus(200).setMsg("SUCCESS");
        return resp;
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