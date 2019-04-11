package com.zhangwen.learn.zhangwenit.concurrent.aqs;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;
import com.zhangwen.learn.zhangwenit.util.PrintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/11 11:53 AM
 * @Version 1.0
 **/
public class AqsTest {

    public static void main(String[] args) {
        testHead();
    }


    /**
     * 头尾节点变化情况
     */
    private static void testHead() {
        final ReentrantLock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                lock.lock();
                try {
                    PrintUtils.printWithTime("get lock");
                    SleepUtils.second(100);
                } finally {
                    lock.unlock();
                }
            });
            PrintUtils.printWithTime("run + " + i);
        }
    }
}