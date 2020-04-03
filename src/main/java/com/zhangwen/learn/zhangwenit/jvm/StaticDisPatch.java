package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description 静态分派
 * @Author ZWen
 * @Date 2020/4/3 11:16 PM
 * @Version 1.0
 **/
public class StaticDisPatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class WoMan extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("hello human");
    }

    public void sayHello(WoMan human) {
        System.out.println("hello woman");
    }

    public void sayHello(Man human) {
        System.out.println("hello man");
    }

    public static void main(String[] args) {
//        Man man = new Man();
//        WoMan woMan = new WoMan();
//        StaticDisPatch staticDisPatch = new StaticDisPatch();
//        staticDisPatch.sayHello(man);
//        staticDisPatch.sayHello(woMan);
        Human man = new Man();
        Human woMan = new WoMan();
        StaticDisPatch staticDisPatch = new StaticDisPatch();
        staticDisPatch.sayHello(man);
        staticDisPatch.sayHello(woMan);
    }

}