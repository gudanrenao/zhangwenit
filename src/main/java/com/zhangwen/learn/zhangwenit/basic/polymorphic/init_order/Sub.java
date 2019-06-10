package com.zhangwen.learn.zhangwenit.basic.polymorphic.init_order;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/30 10:00 PM
 * @Version 1.0
 **/
public class Sub extends Super {

    private int i = 2;

    void hello() {
        System.out.println("sub hello i is " + i);
    }

    public Sub(int i) {
        this.i = i;
        System.out.println("sub constructor .. i is " + i);
    }

    /**
     * 初始化顺序
     * 1.将分配给对象的存储空间初始化为二进制的0
     * 2.调用基类构造器
     * 3.按照声明的顺序调用成员的初始化方法
     * 4.调用当前类的构造器主体
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 由于父类构造器执行时，子类field还没有初始化，所有多态调用子类hello()时，i还没有初始化，为0
         */
        new Sub(5);
    }
}