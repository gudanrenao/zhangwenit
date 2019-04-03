package com.zhangwen.learn.zhangwenit.synchronous;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description synchronized 内存语义：释放锁的线程向获取该锁的线程发送消息
 * @Author ZWen
 * @Date 2019/4/3 10:13 AM
 * @Version 1.0
 **/
public class CountDemo {

    private int count = 0;

    private synchronized void addOne() {
        count++;
    }

    public static void main(String[] args) {
        CountDemo countDemo = new CountDemo();
        long start = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    countDemo.addOne();
                }
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count = " + countDemo.count);
        System.out.println("use time = " + (System.currentTimeMillis() - start));
    }
}