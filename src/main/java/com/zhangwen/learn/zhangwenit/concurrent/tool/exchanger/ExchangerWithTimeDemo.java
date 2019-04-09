package com.zhangwen.learn.zhangwenit.concurrent.tool.exchanger;

import com.zhangwen.learn.zhangwenit.concurrent.util.SleepUtils;

import java.util.concurrent.*;

/**
 * @Description 限时交换等待
 * @Author ZWen
 * @Date 2019/4/9 11:26 AM
 * @Version 1.0
 **/
public class ExchangerWithTimeDemo {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            String a = "a-data";
            try {
                String exchangeB = exchanger.exchange(a, 1, TimeUnit.SECONDS);
                System.out.printf("a thread : a[%s],exchangeB[%s]\n", a, exchangeB);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            String b = "b-data";
            try {
                SleepUtils.second(2);
                //如果在设定等待时间内，两个线程中有任何一个没有执行到exchange，那么两个线程都会抛出java.util.concurrent.TimeoutException
                String exchangeA = exchanger.exchange(b, 1, TimeUnit.SECONDS);
                System.out.printf("b thread : b[%s],exchangeA[%s]\n", b, exchangeA);
                System.out.println("a-data" + (b.equals(exchangeA) ? " " : " not ") + "equal b-data");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
                System.out.println("超时异常---记录");
            }
        });

        executorService.shutdown();
    }
}