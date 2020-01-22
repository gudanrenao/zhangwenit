package com.zhangwen.learn.zhangwenit.basic.java.nio.demo;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/22 2:11 PM
 * @Version 1.0
 **/
public class ServerDemo {

    public static void main(String[] args) {
        MultiplexerServer multiplexerServer = new MultiplexerServer(8080);
        new Thread(multiplexerServer).start();
    }
}