package com.zhangwen.learn.zhangwenit.basic.grammer;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/8 11:34 AM
 * @Version 1.0
 **/
public class InnerClassTest {

    public static void main(String[] args) {
        demo();
        //对于静态内部类，不需要绑定在外部类的实例上
        new InnerClassDemo.InnerStaticClass().test();
    }

    public static void demo() {

        //对于非静态内部类，内部类的实例一定要绑定在外部类的实例中
        InnerClassDemo.InnerClass innerClass = new InnerClassDemo().new InnerClass();
        innerClass.test();
    }
}