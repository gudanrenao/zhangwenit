package com.zhangwen.learn.zhangwenit.basic.io.classes_demo;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/10 4:41 PM
 * @Version 1.0
 **/
public class PrintWriterDemo {

    private static final String printPath = "/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/io/file/printWriter/demo1.txt";

    public static void main(String[] args) throws Exception {
        println();
    }

    private static void println() throws IOException {
        PrintWriter printWriter = new PrintWriter(printPath);
        String s = BufferedReaderDemo.readLine();
        printWriter.println(s);
        printWriter.close();
    }
}