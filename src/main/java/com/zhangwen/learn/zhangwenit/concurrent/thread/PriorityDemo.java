package com.zhangwen.learn.zhangwenit.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description 测试线程优先级
 * @Author ZWen
 * @Date 2019/4/4 9:07 AM
 * @Version 1.0
 **/
public class PriorityDemo {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    /**
     * 测试结果：优先级1和10的结果相似，这表明程序正确性不能依赖线程的优先级高低
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        List<JobDemo> jobs = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            JobDemo jobDemo = new JobDemo(priority);
            jobs.add(jobDemo);
            Thread thread = new Thread(jobDemo,"Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for (JobDemo job : jobs) {
            System.out.println("job priority : " + job.priority + " ,count : " + job.jobCount);
        }
    }

    static class JobDemo implements Runnable {

        private int priority;
        private long jobCount;

        public JobDemo(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}