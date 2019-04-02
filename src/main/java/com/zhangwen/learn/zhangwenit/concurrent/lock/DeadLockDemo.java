package com.zhangwen.learn.zhangwenit.concurrent.lock;

/**
 * @Description 死锁demo
 * @Author ZWen
 * @Date 2019/4/2 4:16 PM
 * @Version 1.0
 **/
public class DeadLockDemo {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void lock1(){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + "  lock1 is locking");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2();
            System.out.println(Thread.currentThread().getName() + "  lock2 has locked");
        }
    }

    public void lock2(){
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName() + "  lock2 is locking");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1();
            System.out.println(Thread.currentThread().getName() + "  lock1 has locked");
        }
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Thread thread1 = new Thread(() -> {
            deadLockDemo.lock1();
        });
        Thread thread2 = new Thread(() -> {
            deadLockDemo.lock2();
        });

        thread1.start();
        thread2.start();



    }

}