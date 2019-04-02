package com.zhangwen.learn.zhangwenit.concurrent.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description cas操作 count demo
 *  cas存在的问题：
 *      1，ABA问题
 *          AtomicStampedReference解决
 *      2，循环时间长开销大
 *      3，只能保证一个共享变量的原子操作
 *          AtomicReference解决
 * @Author ZWen
 * @Date 2019/4/2 6:48 PM
 * @Version 1.0
 **/
public class CasCountDemo {

    private AtomicInteger casCount = new AtomicInteger(0);
    private int count = 0;

    public static void main(String[] args) {
        CasCountDemo casDemo = new CasCountDemo();
        long start = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>(200);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    casDemo.addOne();
                    casDemo.casAddOne();
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

        System.out.println("count = " + casDemo.count   );
        System.out.println("casCount = " + casDemo.casCount.get());
        System.out.println("use time = " + (System.currentTimeMillis() - start));

    }


    /**
     * cas
     */
    private void casAddOne() {
        for (; ; ) {
            int c = casCount.get();
            boolean set = casCount.compareAndSet(c, ++c);
            if (set) {
                break;
            }
        }
    }

    private void addOne() {
        count++;
    }
}