package com.zhangwen.learn.zhangwenit.basic.polymorphic.sub_return_type;

/**
 * @Description 协议返回类型
 * @Author ZWen
 * @Date 2019/5/31 2:34 PM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        /**
         * 子类覆盖父类方法，子类方法的返回值可以是父类方法的子类
         */
        System.out.println(new Super().demo());
        System.out.println(new Sub().demo());
    }
}