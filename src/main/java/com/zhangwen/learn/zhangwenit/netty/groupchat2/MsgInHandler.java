package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 7:52 PM
 * @Version 1.0
 **/
public class MsgInHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到消息： " + msg);
        UserDto userDto = JSONObject.parseObject(msg, UserDto.class);
        String message = "【" + userDto.getUserName() + "】: " + userDto.getMsg();
        //将消息转发给其他客户端，且排除自己
        channelGroup.forEach(ch -> {
            if (ch != ctx.channel()) {
                ch.writeAndFlush(message);
            } else {
                ch.writeAndFlush("你自己刚才说: " + userDto.getMsg());
            }
        });
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("客户端【" + ctx.channel().remoteAddress() + "】加入聊天！");
        channelGroup.writeAndFlush("客户端【" + ctx.channel().remoteAddress() + "】加入聊天！");
        channelGroup.add(ctx.channel());
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        channelGroup.writeAndFlush("客户端【" + ctx.channel().remoteAddress() + "】离开聊天！");
        System.out.println("channelGroup size = " + channelGroup.size());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            System.out.println("心跳事件发生：" + stateEvent.state() + "; isFirst : " + stateEvent.isFirst());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("发生写异常，" + cause.getMessage());
        ctx.close();
    }
}