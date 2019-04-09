package com.zhangwen.learn.zhangwenit.concurrent.tool.cyclicBarrier;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description CyclicBarrier方法
 * @Author ZWen
 * @Date 2019/4/8 10:35 PM
 * @Version 1.0
 **/
public class CyclicBarrierMethodTest {

    private static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    private static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(3);

    public static void main(String[] args) {
//        interruptTest(true);
        getNumberWaitingTest();
    }

    @SuppressWarnings("all")
    private static void interruptTest(final boolean retry) {
        System.out.println("=================" + retry);
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier1.await();
                System.out.println("success execute");
            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
            }
        }, "thread-1");

        thread.start();
        if (!retry) {
            SleepUtils.second(1);
        }
        thread.interrupt();
        try {
            cyclicBarrier1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("isBroken : " + cyclicBarrier1.isBroken());
            if (retry) {
                //重置
                cyclicBarrier1.reset();
                interruptTest(false);
            } else {
                throw new RuntimeException("fail execute");
            }
        }
    }

    @SuppressWarnings("all")
    private static void getNumberWaitingTest() {
        Thread thread1 = new Thread(() -> {
            try {
                SleepUtils.second(3);
                System.out.println("success execute - " + Thread.currentThread().getName());
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
            }
        }, "thread-1");

        Thread thread2 = new Thread(() -> {
            try {
                SleepUtils.second(1);
                System.out.println("success execute - " + Thread.currentThread().getName());
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
            }
        }, "thread-2");

        thread1.start();
        thread2.start();
        SleepUtils.second(2);

        System.out.println("waitingNumber is " + cyclicBarrier2.getNumberWaiting());

        try {
            cyclicBarrier2.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("isBroken : " + cyclicBarrier2.isBroken());
        }
    }

}