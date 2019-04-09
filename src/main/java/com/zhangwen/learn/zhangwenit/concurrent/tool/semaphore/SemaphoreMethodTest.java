package com.zhangwen.learn.zhangwenit.concurrent.tool.semaphore;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description 测试Semaphore方法
 * @Author ZWen
 * @Date 2019/4/9 10:49 AM
 * @Version 1.0
 **/
public class SemaphoreMethodTest {

    public static void main(String[] args) {
//        availablePermitsTest();
        getQueueLengthTest();
    }

    /**
     * 信号量中可用的许可证数量
     */
    private static void availablePermitsTest() {
        Semaphore semaphore = new Semaphore(10);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            executorService.execute(() -> {
                try {
                    //如果每个需要占用两个许可，所以就只有5个并发量了
                    semaphore.acquire(1);
                    SleepUtils.second(1);
                    System.out.println(Thread.currentThread() + " running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1);
                }
            });
        }
        System.out.println("main 剩余可用许可证为：" + semaphore.availablePermits());
        System.out.println("main 剩余可用许可证为：" + semaphore.availablePermits());
        SleepUtils.second(1);
        System.out.println("main 剩余可用许可证为：" + semaphore.availablePermits());
        executorService.shutdown();
    }

    /**
     * 返回正在等待许可证的线程数量
     */
    private static void getQueueLengthTest() {
        Semaphore semaphore = new Semaphore(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 8; i++) {
            executorService.execute(() -> {
                try {
                    //如果每个需要占用两个许可，所以就只有5个并发量了
                    semaphore.acquire(2);
                    SleepUtils.second(2);
                    System.out.println(Thread.currentThread() + " running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
            });
        }
        SleepUtils.second(1);
        System.out.println("main 等待许可证的线程数量：" + semaphore.getQueueLength());
        SleepUtils.second(2);
        System.out.println("main 等待许可证的线程数量：" + semaphore.getQueueLength());
        executorService.shutdown();
    }
}