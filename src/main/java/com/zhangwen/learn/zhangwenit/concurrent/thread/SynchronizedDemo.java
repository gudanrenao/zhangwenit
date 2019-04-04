package com.zhangwen.learn.zhangwenit.concurrent.thread;

/**
 * @Description 使用javap 查看 Synchronized 方法 class 文件
 * @Author ZWen
 * @Date 2019/4/4 2:13 PM
 * @Version 1.0
 **/
public class SynchronizedDemo {

    public static void main(String[] args) {
        //对SynchronizedDemo class对象加锁
        synchronized (SynchronizedDemo.class) {

        }
        //静态同步方法，对SynchronizedDemo class对象加锁
        m();
    }

    public static synchronized void m() {
    }
}