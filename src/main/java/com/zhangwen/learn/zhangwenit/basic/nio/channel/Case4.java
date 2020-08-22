package com.zhangwen.learn.zhangwenit.basic.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description 拷贝文件
 * @Author ZWen
 * @Date 2020/6/4 11:52 AM
 * @Version 1.0
 **/
public class Case4 {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/bigImage.png");
        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/copyImage.png");
        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,inputStreamChannel.position(),inputStreamChannel.size());

        inputStream.close();
        outputStream.close();


    }
}