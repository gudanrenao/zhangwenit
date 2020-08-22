package com.zhangwen.learn.zhangwenit.basic.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description 将一个文件的数据放到另一个文件 用一个buff  read - write
 * @Author ZWen
 * @Date 2020/6/4 11:52 AM
 * @Version 1.0
 **/
public class Case3 {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/case1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channelRead = fileInputStream.getChannel();


        File outFile = new File("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/out1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        FileChannel channelWrite = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        int read = 0;
        while((read = channelRead.read(byteBuffer)) != -1){
            System.out.println("read is " + read);
            byteBuffer.flip();
            channelWrite.write(byteBuffer);
            byteBuffer.clear();
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}