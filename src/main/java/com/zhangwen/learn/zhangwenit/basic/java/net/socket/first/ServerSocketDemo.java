package com.zhangwen.learn.zhangwenit.basic.java.net.socket.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/1/9 12:27 AM
 * @Version 1.0
 **/
public class ServerSocketDemo {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            Socket accept = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));

            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}