package com.zhangwen.learn.zhangwenit.concurrent.threadPool.ThreadPoolExecutor;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description 有返回值的执行任务
 * @Author ZWen
 * @Date 2019/4/9 5:04 PM
 * @Version 1.0
 **/
public class FutureTaskDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<?> future = executorService.submit(() -> {
            long millis = System.currentTimeMillis();
            SleepUtils.second(1);
            return "time " + millis;
        });

        try {
            Object o = future.get();
            System.out.println("执行结果：" + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程被中断");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("线程执行异常");
        } finally {
            executorService.shutdown();
        }

    }
}