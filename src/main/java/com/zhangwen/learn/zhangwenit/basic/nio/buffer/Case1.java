package com.zhangwen.learn.zhangwenit.basic.nio.buffer;

import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/2 11:45 PM
 * @Version 1.0
 **/
public class Case1 {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        IntStream.range(0,5).map(e -> e * 2).forEach(intBuffer::put);
        intBuffer.flip();
//        while (intBuffer.hasRemaining()){
//            System.out.println(intBuffer.get());
//        }
        System.out.println(intBuffer.get());
        intBuffer.flip();
        intBuffer.put(100);
//        intBuffer.put(200);
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}