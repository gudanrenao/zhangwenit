package com.zhangwen.learn.zhangwenit.basic.io.classes_demo;

import org.apache.tomcat.util.net.openssl.OpenSSLUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/10 12:10 PM
 * @Version 1.0
 **/
public class BufferedReaderDemo {

    private static final String helloFile = "/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/io/file/hello.txt";

    public static void main(String[] args) throws Exception {
        String s = readLine();
        System.out.println(s);
    }

    /**
     * 注意：readLine()会忽略最后的换行符
     */
    public static String readLine() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(helloFile));
        String result = "";
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            result += (line + System.lineSeparator());
        }
        bufferedReader.close();
        return result;
    }
}