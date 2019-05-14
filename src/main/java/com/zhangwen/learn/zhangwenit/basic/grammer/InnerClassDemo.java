package com.zhangwen.learn.zhangwenit.basic.grammer;

/**
 * @Description 静态内部类和非静态内部类的区别：
 * <p>
 * 一、如是否可以创建静态的成员方法与成员变量(静态内部类可以创建静态的成员，而非静态的内部类不可以)
 * 二、对于访问外部类的成员的限制(静态内部类只可以访问外部类中的静态成员变量与成员方法，而非静态的内部类即可以访问所有的外部类成员方法与成员变量)。
 * @Author ZWen
 * @Date 2019/5/8 11:27 AM
 * @Version 1.0
 **/
public class InnerClassDemo {

    private String name = "null";

    private static String staticField = "hello";

    private static void staticMethod() {
        System.out.println("outer class static method run");
    }

    private void unStaticMethod() {
        System.out.println("outer class unStatic method run");
    }

    public static class InnerStaticClass {

        /**
         * 静态内部类只可以访问外部类中的静态成员变量与成员方法
         */
        public void test() {
            InnerClassDemo innerClassDemo = new InnerClassDemo();
            System.out.println(innerClassDemo.name);
        }
    }

    public class InnerClass {
        /**
         * todo:非静态内部类可以包含 final修饰的静态简单数据类型字段(Integer不可以)，为什么？
         */
        private final static int age = 10;

        /**
         * 非静态的内部类即可以访问所有的外部类成员方法与成员变量
         */
        public void test() {
            unStaticMethod();
            System.out.println(name);
        }
    }
}