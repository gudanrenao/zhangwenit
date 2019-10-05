package com.zhangwen.learn.zhangwenit.designmodel.facade.first;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/10/5 11:06 AM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        System.out.println("==========================================");
        facade.methodB();
    }
}