package com.zhangwen.learn.zhangwenit.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description count demo 计算1+2+3+4... 分为子任务执行
 * todo:源码待理解
 * @Author ZWen
 * @Date 2019/4/8 5:17 PM
 * @Version 1.0
 **/
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int begin;
    private int end;

    public CountTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - begin) <= THRESHOLD;
        if (canCompute) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阈值，就分裂为两个子任务执行
            int middle = (begin + end) / 2;
            CountTask leftTask = new CountTask(begin, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务完成，并获得结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            //合并结果
            sum += leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 100);

        Integer invoke = pool.invoke(countTask);

        System.out.println(invoke);

        //异常处理 ,该方法检查任务是否被取消或者抛出异常
        if(countTask.isCompletedAbnormally()){
            System.out.println(countTask.getException());
        }

    }
}