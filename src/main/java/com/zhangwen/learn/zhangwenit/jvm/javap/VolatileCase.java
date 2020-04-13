package com.zhangwen.learn.zhangwenit.jvm.javap;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/9 4:44 PM
 * @Version 1.0
 **/
public class VolatileCase {

    private static volatile int count = 0;

    private static final int THREAD_NUM = 20;

    public static void add() {
        for (int i = 0; i < 10000; i++) {
            //因为count++会产生多个指令，当getstatic指令将count取到操作数栈顶时，volatile保证此时的count
            //是正确的，但是当执行iconst_1、iadd指令时，其他线程可能已经把count的值修改了，这次操作数栈顶的数据
            //就变为了过期的脏数据，最终putstatic指令执行后可能将较小的count值同步回主内存中
            count++;
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(VolatileCase::add);
            threads[i].start();
        }
        //等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }
}