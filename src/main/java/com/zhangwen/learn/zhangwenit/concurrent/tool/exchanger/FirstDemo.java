package com.zhangwen.learn.zhangwenit.concurrent.tool.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 用于线程间协作
 * 它提供一个同步点，在这个同步点，两个线程可以交换各自的数据
 * 通过exchange()方法，如果一个线程先执行了，它会等待第二个线程也执行exchange(),都到达同步点时，才交换数据
 * @Author ZWen
 * @Date 2019/4/9 11:12 AM
 * @Version 1.0
 **/
public class FirstDemo {

    /**
     * todo:源码待理解
     * 使用场景:
     * 1.校对工作，比如两个人录入的数据分别在两个sheet，每个线程录入其中一个，然后比较两个sheet的数据的结果是否一致
     * 2.遗传算法：选出两个人作为交配对象，这时会交换两人的数据，使用交叉规则得出两个交配结果
     *
     * @param args
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            String a = "a-data";
            try {
                String exchangeB = exchanger.exchange(a);
                System.out.printf("a thread : a[%s],exchangeB[%s]\n", a, exchangeB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            String b = "b-data";
            try {
//                SleepUtils.second(1);
                String exchangeA = exchanger.exchange(b);
                System.out.printf("b thread : b[%s],exchangeA[%s]\n", b, exchangeA);
                System.out.println("a-data" + (b.equals(exchangeA) ? " " : " not ") + "equal b-data");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }
}