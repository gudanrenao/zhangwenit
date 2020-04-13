package com.zhangwen.learn.zhangwenit.basic.java.lang;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/13 10:40 PM
 * @Version 1.0
 **/
public class StringCase {

    public static void main(String[] args) {
        equalTest1();
        equalTest2();
    }

    /**
     * true
     */
    private static void equalTest1() {
        //这里会有常量优化技术，将三个字符相加后的abc放入常量池
        String str1 = "a" + "b" + "c";
        //这里由于常量池中已有abc，所以也是abc在常量池中的地址
        String str2 = "abc";
        System.out.println(str1 == str2);
    }

    /**
     * false
     */
    private static void equalTest2() {

        String str1 = "a";
        //这里指向abc在常量池中的地址
        String str2 = "abc";
        //这里会使用 StringBuilder.append()在堆内存生成一个对象，赋值给str3的时候，调用toString又生成一个对象
        String str3 = str1 + "bc";
        System.out.println(str3 == str2);
    }
}