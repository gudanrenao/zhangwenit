package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description 死锁测试
 * @Author ZWen
 * @Date 2020/3/29 9:02 PM
 * @Version 1.0
 **/
public class DeadLock_TestCase {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private void run1() {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("run1 lock1 ....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("run1 lock2 ....");
                }
            }
        }).start();

    }

    private void run2() {
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("run2 lock2 ....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("run2 lock1 ....");
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        DeadLock_TestCase testCase = new DeadLock_TestCase();
        testCase.run1();
        testCase.run2();
    }
}