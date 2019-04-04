package com.zhangwen.learn.zhangwenit.concurrent.thread;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/4 7:02 PM
 * @Version 1.0
 **/
public class JoinDemo {

    public static void main(String[] args) {

        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //每个线程拥有前一个线程的引用，需要等前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Join(previous), "thread-" + i);
            thread.start();
            previous = thread;
        }
        SleepUtils.second(3);
        System.out.println(Thread.currentThread() + ".... terminate  end");
    }

    static class Join implements Runnable {

        private Thread thread;

        public Join(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + ".... terminate");
        }
    }
}