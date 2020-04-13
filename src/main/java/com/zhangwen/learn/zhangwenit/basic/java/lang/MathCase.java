package com.zhangwen.learn.zhangwenit.basic.java.lang;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/10 6:28 PM
 * @Version 1.0
 **/
public class MathCase {

    public static void main(String[] args) {

        System.out.println(Math.round(12.500012D));
        System.out.println(Math.nextDown(12.500012D));
        System.out.println(Math.nextUp(12.500012D));
        System.out.println(Math.nextAfter(12.500012D,12.4));
        System.out.println(Math.abs(-12.500012D));


    }
}