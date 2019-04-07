package com.zhangwen.learn.zhangwenit.concurrent.lock.custom;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Description twinsLock测试
 * @Author ZWen
 * @Date 2019/4/7 1:54 PM
 * @Version 1.0
 **/
public class TwinsLockTest {

    private static TwinsLock twinsLock = new TwinsLock();

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Twin(),"thread-" + i);
            thread.start();
        }
        for (int i = 1; i <= 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

    private static class Twin implements Runnable {

        @Override
        public void run() {
            twinsLock.lock();
            try {
                SleepUtils.second(1);
                System.out.println(Thread.currentThread() + "---" + DateFormatUtils.format(new Date(), "HH:mm:ss"));
                SleepUtils.second(1);
            } finally {
                twinsLock.unlock();
            }
        }
    }
}