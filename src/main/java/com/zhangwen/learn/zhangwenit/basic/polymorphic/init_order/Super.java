package com.zhangwen.learn.zhangwenit.basic.polymorphic.init_order;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/30 9:58 PM
 * @Version 1.0
 **/
public class Super {

    void hello(){
        System.out.println("super hello");
    }

    public Super() {
        System.out.println("before hello");
        hello();
        System.out.println("after hello");
    }
}