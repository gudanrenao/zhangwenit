package com.zhangwen.learn.zhangwenit.concurrent.tool.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/9 10:23 AM
 * @Version 1.0
 **/
public class FirstTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(new Down(countDownLatch), "thread-" + i).start();
        }
        countDownLatch.await();
        System.out.println("finished...");
    }

    static class Down implements Runnable {

        private CountDownLatch countDownLatch;

        public Down(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread() + " running ...");
            } finally {
                countDownLatch.countDown();
            }

        }
    }
}