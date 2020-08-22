package com.zhangwen.learn.zhangwenit.basic.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description 将文件数据读取出来
 * @Author ZWen
 * @Date 2020/6/4 11:52 AM
 * @Version 1.0
 **/
public class Case2 {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/case1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(buffer);

        buffer.flip();

//        byte[] result = new byte[buffer.limit() - buffer.position()];
//        buffer.get(result,buffer.position(),result.length);
//        System.out.println(new String(result));

        System.out.println(new String(buffer.array()));

        fileInputStream.close();
    }
}