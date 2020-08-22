package com.zhangwen.learn.zhangwenit.concurrent.collection.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description todo:源码待理解
 * @Author ZWen
 * @Date 2019/4/9 11:05 AM
 * @Version 1.0
 **/
public class FirstTest {

    public static void main(String[] args) {
        System.out.println("begin==============");
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(8);
        System.out.println("end================");
    }
}