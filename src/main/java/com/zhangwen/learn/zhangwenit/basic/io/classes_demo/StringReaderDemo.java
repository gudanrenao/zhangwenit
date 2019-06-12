package com.zhangwen.learn.zhangwenit.basic.io.classes_demo;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/10 3:16 PM
 * @Version 1.0
 **/
public class StringReaderDemo {

    public static void main(String[] args) throws Exception {
        read();
    }

    private static void read() throws IOException {
        StringReader stringReader = new StringReader(BufferedReaderDemo.readLine());
        int c;
        while ((c = stringReader.read()) != -1) {
            System.out.print((char) c);
        }
        stringReader.close();
    }
}