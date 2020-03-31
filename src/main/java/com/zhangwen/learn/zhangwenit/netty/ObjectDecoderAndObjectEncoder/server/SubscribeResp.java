package com.zhangwen.learn.zhangwenit.netty.ObjectDecoderAndObjectEncoder.server;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/27 1:15 PM
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class SubscribeResp implements Serializable {

    private static final long serialVersionUID = -5119189025431685358L;

    private int id;

    private int status;

    private String msg;
}