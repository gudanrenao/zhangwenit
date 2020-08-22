package com.zhangwen.learn.zhangwenit.basic.java.dto;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/8/22 7:21 PM
 * @Version 1.0
 **/
public class Dog implements Animal {
    @Override
    public void printName() {
        System.out.println("this is Dog");
    }
}