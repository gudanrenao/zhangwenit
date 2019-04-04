package com.zhangwen.learn.zhangwenit.concurrent.happenBefore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description volatile happen-before todo:没测出来效果
 * @Author ZWen
 * @Date 2019/4/3 3:58 PM
 * @Version 1.0
 **/
public class VolatileDemo {

    private int a;
    private int b;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private void set() {
        b = 2;
        a = 1;
    }

    private void get() {
//        if (a != 1) {
//            atomicInteger.incrementAndGet();
//        }
        if (a != 1 && b == 2) {
            atomicInteger.incrementAndGet();
        }
        System.out.println(a + "---" + b);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            VolatileDemo volatileDemo = new VolatileDemo();
            Thread thread1 = new Thread(() -> {
                volatileDemo.set();
            });
            Thread thread2 = new Thread(() -> {
                volatileDemo.get();
            });
            if (i % 2 == 0) {
                thread2.start();
                thread1.start();
            } else {
                thread1.start();
                thread2.start();
            }
        }

        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("not safe count is " + atomicInteger.get());
    }
}