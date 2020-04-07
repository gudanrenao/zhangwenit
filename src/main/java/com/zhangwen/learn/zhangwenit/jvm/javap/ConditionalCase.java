package com.zhangwen.learn.zhangwenit.jvm.javap;

/**
 * @Description 条件编译
 * @Author ZWen
 * @Date 2020/4/7 2:38 PM
 * @Version 1.0
 **/
public class ConditionalCase {

    public static void main(String[] args) {
        //if(true)打断点不会中断，因为条件编译后，只有一条 System.out.println("block true"); 语句存在在class文件中
        if (true) {
            System.out.println("block true");
        } else {
            System.out.println("block false");
        }
    }
}