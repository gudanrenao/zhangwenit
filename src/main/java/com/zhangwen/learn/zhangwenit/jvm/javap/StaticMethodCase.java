package com.zhangwen.learn.zhangwenit.jvm.javap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/3 11:05 PM
 * @Version 1.0
 **/
public class StaticMethodCase {

    public static void sayHello(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        //invokestatic 指令
        StaticMethodCase.sayHello();

        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);
        list.forEach(System.out::println);

        //下面这个会在编译期的标注检查阶段进行常亮折叠的代码优化  String var2 = "123456";
        String add = "123" + "456";

        //下面这个会在编译期的字节码生成阶段把字符串的加操作替换为StringBuffer或StringBuilder
        String a = "1";
        String b = "2";
        String c = "3";
        String append = a + b + c;
    }
}