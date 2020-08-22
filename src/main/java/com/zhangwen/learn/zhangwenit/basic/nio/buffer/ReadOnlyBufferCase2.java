package com.zhangwen.learn.zhangwenit.basic.nio.buffer;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

/**
 * @Description 只读buffer
 * @Author ZWen
 * @Date 2020/6/4 3:42 PM
 * @Version 1.0
 **/
public class ReadOnlyBufferCase2 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        IntStream.range(0,8).forEach(e -> byteBuffer.put((byte)e));

        byteBuffer.flip();

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte) 1);
    }
}