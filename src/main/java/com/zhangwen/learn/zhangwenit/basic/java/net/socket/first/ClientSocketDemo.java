package com.zhangwen.learn.zhangwenit.basic.java.net.socket.first;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/9 12:30 AM
 * @Version 1.0
 **/
public class ClientSocketDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("hello socket");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}