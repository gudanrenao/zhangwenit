package com.zhangwen.learn.zhangwenit.basic.java.nio.demo;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/22 4:33 PM
 * @Version 1.0
 **/
public class ClientDemo {

    public static void main(String[] args) {
        new Thread(new ClientHandle("127.0.0.1", 8080)).start();
    }
}