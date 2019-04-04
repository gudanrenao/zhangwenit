package com.zhangwen.learn.zhangwenit.concurrent.thread;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

/**
 * @Description 中断线程的两种方式：中断操作(interrupt) 标志位(boolean变量)
 * @Author ZWen
 * @Date 2019/4/4 1:35 PM
 * @Version 1.0
 **/
public class ShutdownDemo {

    /**
     * 这两种方式可以让被终止线程有机会去清理资源
     *
     * @param args
     */
    public static void main(String[] args) {

        CountDemo countDemo1 = new CountDemo();
        Thread countStopWithInterrupt = new Thread(countDemo1, "countStopWithInterrupt");
        countStopWithInterrupt.start();
        SleepUtils.second(1);
        countStopWithInterrupt.interrupt();

        CountDemo countDemo2 = new CountDemo();
        Thread countStopWithFlag = new Thread(countDemo2, "countStopWithFlag");
        countStopWithFlag.start();
        SleepUtils.second(1);
        countDemo2.cancel();
    }

    static class CountDemo implements Runnable {

        private long count;
        private volatile boolean flag = true;

        @Override
        public void run() {
            while (flag && !Thread.currentThread().isInterrupted()) {
                count++;
            }

            System.out.println("count is " + count);
        }

        private void cancel() {
            flag = false;
        }
    }
}