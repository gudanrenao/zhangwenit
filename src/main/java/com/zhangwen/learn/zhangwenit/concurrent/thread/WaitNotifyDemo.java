package com.zhangwen.learn.zhangwenit.concurrent.thread;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Description 等待通知机制
 * @Author ZWen
 * @Date 2019/4/4 5:39 PM
 * @Version 1.0
 **/
public class WaitNotifyDemo {

    static boolean flag = true;
    static final Object lock = new Object();

    /**
     * 1.调用wait()/notify()/notifyAll()时，需要相对调用对象加锁
     * 2.调用wait()后，线程状态由RUNNING变为WAITING，并将当前线程放到锁对象的等待队列
     * 3.调用notify()/notifyAll()后，等待线程不会从wait()中返回，需要调用notify()/notifyAll()的线程释放锁之后，等待线程才有机会从wait()返回
     * 4.notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列，notifyAll()方法将等待队列中的所有的等待线程从等待队列全部移到同步队列，被移动的线程的状态从WAITING变为BLOCKED
     * 5.从wait()方法返回的前提是获得了调用对象的锁
     *
     * 从以上5点可以看出，等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能否感知到通知线程对变量做出的修改
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread wait = new Thread(new Wait(), "waitThread");
        wait.start();
        SleepUtils.second(1);

        Thread notify = new Thread(new Notify(), "notifyThread");
        notify.start();

    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时，wait,同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true,waiting... " + DateFormatUtils.format(new Date(), "mm:HH:ss"));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false,running... " + DateFormatUtils.format(new Date(), "mm:HH:ss"));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取到lock的锁，然后进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock的锁后(也就是当前同步代码块执行完之后)，waitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock,notify... " + DateFormatUtils.format(new Date(), "mm:HH:ss"));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
//            SleepUtils.second(1);
            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again,sleep 5 seconds... " + DateFormatUtils.format(new Date(), "mm:HH:ss"));
                SleepUtils.second(5);
            }
        }
    }
}