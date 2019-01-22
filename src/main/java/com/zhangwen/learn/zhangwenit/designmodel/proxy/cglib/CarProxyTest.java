package com.zhangwen.learn.zhangwenit.designmodel.proxy.cglib;

/**
 * @Description cglib代理模式测试
 * @Author ZWen
 * @Date 2019/1/17 5:48 PM
 * @Version 1.0
 **/
public class CarProxyTest {

    public static void main(String[] args) {

        Car proxy = (Car) new CarProxy().getProxy(Car.class);

        proxy.getName("bmw");

    }
}