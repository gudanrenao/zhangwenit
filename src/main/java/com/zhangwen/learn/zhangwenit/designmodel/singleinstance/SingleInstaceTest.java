package com.zhangwen.learn.zhangwenit.designmodel.singleinstance;

/**
 * @Description 单例测试
 * @Author ZWen
 * @Date 2019/1/19 10:30 AM
 * @Version 1.0
 **/
public class SingleInstaceTest {

    public static void main(String[] args) {
//        SingleInstance1 instance = SingleInstance1.getInstance();
//        System.out.println(instance);
//        SingleInstance1 instance2 = SingleInstance1.getInstance();
//        System.out.println(instance2);
//        System.out.println(instance == instance2);

//        SingleInstance2 instance = SingleInstance2.getInstance();
//        System.out.println(instance);
//        SingleInstance2 instance2 = SingleInstance2.getInstance();
//        System.out.println(instance2);
//        System.out.println(instance == instance2);

        SingleInstance3 instance = SingleInstance3.getInstance();
        System.out.println(instance);
        SingleInstance3 instance2 = SingleInstance3.getInstance();
        System.out.println(instance2);
        System.out.println(instance == instance2);
    }
}