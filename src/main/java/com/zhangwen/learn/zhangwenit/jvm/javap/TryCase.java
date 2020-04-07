package com.zhangwen.learn.zhangwenit.jvm.javap;

import java.util.Scanner;

/**
 * @Description 语法糖：try语句定义和关闭资源
 * @Author ZWen
 * @Date 2020/4/7 2:49 PM
 * @Version 1.0
 **/
public class TryCase {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}