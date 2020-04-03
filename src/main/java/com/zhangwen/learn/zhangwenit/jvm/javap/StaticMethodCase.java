package com.zhangwen.learn.zhangwenit.jvm.javap;

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
    }
}