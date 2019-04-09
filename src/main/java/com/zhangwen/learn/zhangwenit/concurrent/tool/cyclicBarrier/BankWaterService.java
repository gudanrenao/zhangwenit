package com.zhangwen.learn.zhangwenit.concurrent.tool.cyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description 统计流水demo，有size个sheet，求出每个的和，再把size个的和累加
 * @Author ZWen
 * @Date 2019/4/8 10:02 PM
 * @Version 1.0
 **/
public class BankWaterService implements Runnable {

    /**
     * 任务数量
     */
    private int size;

    /**
     * size个子任务
     */
    private CyclicBarrier cyclicBarrier;

    /**
     * size个线程执行
     */
    private ExecutorService executorService;

    /**
     * 保存每个子任务的统计结果
     */
    private Map<String, Integer> bankWaterCount;

    public BankWaterService(int size) {
        this.size = size;
        cyclicBarrier = new CyclicBarrier(size, this);
        executorService = Executors.newFixedThreadPool(size);
        bankWaterCount = new ConcurrentHashMap<>(size);
    }

    /**
     * 执行每个子任务
     */
    public void count() {
        for (int i = 1; i <= size; i++) {
            final int sum = i;
            executorService.execute(() -> {
                bankWaterCount.put(Thread.currentThread().getName(), sum);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    /**
     * 每个sheet计算后，执行累加 (每个子任务到达屏障，执行BarrierAction)
     */
    @Override
    public void run() {
        int totalCount = 0;
        for (Map.Entry<String, Integer> entry : bankWaterCount.entrySet()) {
            totalCount += entry.getValue();
        }
        System.out.println("total count is " + totalCount);
        //关闭线程池,不然main不会停止
        executorService.shutdown();
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService(4);
        bankWaterService.count();

    }
}