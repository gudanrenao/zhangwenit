package com.zhangwen.learn.zhangwenit.concurrent.tool.semaphore;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/9 10:31 AM
 * @Version 1.0
 **/
public class FirstSimpleDemo {

    public static void main(String[] args) {
        //10个最大并发数
        Semaphore semaphore = new Semaphore(10);
        //30个线程并发执行
        int thread_count = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(thread_count);
        for (int i = 1; i <= thread_count; i++) {
            executorService.execute(() -> {
                try {
                    //如果每个需要占用两个许可，所以就只有5个并发量了
                    semaphore.acquire(2);
                    SleepUtils.second(1);
                    System.out.println(Thread.currentThread() + " running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
            });
        }
        executorService.shutdown();

    }
}