package com.zhangwen.learn.zhangwenit.basic.io.classes_demo;

import java.io.*;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/10 3:25 PM
 * @Version 1.0
 **/
public class DataInputAndOutputStreamDemo {

    public static final String hello = "hello!张文";

    private static final String demo = "/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/io/file/dataStream/out.txt";

    public static void main(String[] args) throws Exception {
//        read();
        readAndWrite();
    }

    private static void read() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(hello.getBytes()));
        System.out.println("size " + dataInputStream.available());
        while (dataInputStream.available() != 0) {
            System.out.println(dataInputStream.readByte());
        }
        dataInputStream.close();
    }

    private static void readAndWrite() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(demo)));
        out.writeUTF("one string");
        out.writeUTF("two string");
        out.writeDouble(1.22);
        out.writeUTF("three string");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(demo)));
        int index = 1;
        while (in.available() != 0) {
            if (index == 3) {
                System.out.println(in.readDouble());
            } else {
                System.out.println(in.readUTF());
            }
            index++;
        }
        in.close();
    }
}