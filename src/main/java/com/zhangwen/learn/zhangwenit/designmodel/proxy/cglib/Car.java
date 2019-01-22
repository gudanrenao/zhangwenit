package com.zhangwen.learn.zhangwenit.designmodel.proxy.cglib;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/15 5:37 PM
 * @Version 1.0
 **/
public class Car {

    public String getName(String name){
        System.out.println("car name is " + name);
        return name;
    }
}