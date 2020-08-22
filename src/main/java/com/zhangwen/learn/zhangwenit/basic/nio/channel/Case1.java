package com.zhangwen.learn.zhangwenit.basic.nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description 将数据写入到文件中
 * @Author ZWen
 * @Date 2020/6/4 11:52 AM
 * @Version 1.0
 **/
public class Case1 {

    public static void main(String[] args) throws IOException {
        String context = "nihao，张文";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(context.getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/nio/channel/case1.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        //将buffer写入到channel，先反转
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        //关闭流
        fileOutputStream.close();
    }
}