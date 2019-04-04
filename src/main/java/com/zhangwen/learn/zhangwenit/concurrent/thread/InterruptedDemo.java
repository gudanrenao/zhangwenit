package com.zhangwen.learn.zhangwenit.concurrent.thread;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

/**
 * @Descriptio 测试interrupt中断线程 返回值
 * @Author ZWen
 * @Date 2019/4/4 12:08 PM
 * @Version 1.0
 **/
public class InterruptedDemo {

    public static void main(String[] args) {
        //不停尝试睡眠
        Thread sleepThread = new Thread(new SleepThread(), "sleepThread");
        //不停运行
        Thread busyThread = new Thread(new BusyThread(), "busyThread");
        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //休眠5秒，让上面两个线程充分运行
        SleepUtils.second(5);
        //中断两个线程
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        //休眠两秒，防止sleepThread和busyThread立刻退出
        SleepUtils.second(2);
    }


    static class SleepThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(1);
            }
        }
    }

    static class BusyThread implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}