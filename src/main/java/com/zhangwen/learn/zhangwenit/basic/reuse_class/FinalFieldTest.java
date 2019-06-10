package com.zhangwen.learn.zhangwenit.basic.reuse_class;

/**
 * @Description 测试final修饰的成员变量
 *
 * @Author ZWen
 * @Date 2019/5/30 2:06 PM
 * @Version 1.0
 **/
public class FinalFieldTest {

    public static void main(String[] args) {
        testFinalField();
    }

    public static void testFinalField(){
        DemoClass demoClass1 = new DemoClass(1);
        DemoClass demoClass2 = new DemoClass(2);

        System.out.println(demoClass1);
        System.out.println(demoClass2);
    }
}