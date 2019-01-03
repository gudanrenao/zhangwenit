package com.zhangwen.learn.zhangwenit.basic;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Description IO测试
 * @Author ZWen
 * @Date 2019/1/3 3:43 PM
 * @Version 1.0
 **/
public class IOTest {

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("输入字符串：end 退出");
//        String s;
//        do {
//            s = bufferedReader.readLine();
//            System.out.println(s);
//        } while (!"end".equals(s));

//        Scanner scanner = new Scanner(System.in);
////        if(scanner.hasNextLine()){
////            System.out.println("in : " + scanner.nextLine());
////        }
//        if(scanner.hasNextLong()){
//            System.out.println("nextLong : " + scanner.nextLong());
//        } else {
//            System.out.println("输入的不是整数");
//        }
//        scanner.close();

        InputStream fileInputStream = new FileInputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/test");
        OutputStream fileOutputStream = new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/out");
//        int c;
//        while ((c = fileInputStream.read()) != -1) {
//            System.out.print(c);
//            fileOutputStream.write(c);
//        }
//        fileInputStream.close();
//        fileOutputStream.close();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        outputStreamWriter.write("尚医智信");
        outputStreamWriter.write("\n");
        outputStreamWriter.write("更低的成本");
        outputStreamWriter.close();


    }
}