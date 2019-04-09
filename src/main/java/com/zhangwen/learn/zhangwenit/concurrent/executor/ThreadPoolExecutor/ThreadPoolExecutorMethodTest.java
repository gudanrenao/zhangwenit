package com.zhangwen.learn.zhangwenit.concurrent.executor.ThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description ThreadPoolExecutor 方法测试
 * @Author ZWen
 * @Date 2019/4/9 9:06 PM
 * @Version 1.0
 **/
public class ThreadPoolExecutorMethodTest {

    public static void main(String[] args) {
        getPoolSizeAndActiveCountTest();
    }

    private static void getPoolSizeAndActiveCountTest() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2), new ThreadFactoryBuilder().setNameFormat("my-thread-%s").build(), new ThreadPoolExecutor.AbortPolicy());
        //设置核心线程也会超时销毁。如果设置为true，线程池的最小线程数可以为0，并且构建线程池的时候，超时时间一定要大于0
//        executorService.allowCoreThreadTimeOut(true);
        System.out.println("getPoolSize : " + executorService.getPoolSize());

        for (int i = 0; i < 40; i++) {
            final int ii = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread() + "running...");
                SleepUtils.second(1);
            });
            System.out.println(ii + " getPoolSize : " + executorService.getPoolSize());
        }

        SleepUtils.second(2);
        System.out.println("last getPoolSize : " + executorService.getPoolSize());
        System.out.println("last getActiveCount : " + executorService.getActiveCount());

        executorService.shutdown();
    }
}