package com.zhangwen.learn.zhangwenit.concurrent.tool.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description todo:源码待理解
 * @Author ZWen
 * @Date 2019/4/8 7:22 PM
 * @Version 1.0
 **/
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Test(true), "thread-1");
        Thread thread2 = new Thread(new Test(true), "thread-2");
        Thread thread3 = new Thread(new Test(false), "thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("success");
    }

    static class Test implements Runnable {

        private boolean flag;

        public Test(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
//            if(flag) {
//                System.out.println(Thread.currentThread() + "---" + flag);
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            } else {
//
//            }
            try {
                if (!flag) {
                    throw new RuntimeException("test exception");
                }
            } finally {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread() + "---after wait()");
        }
    }
}