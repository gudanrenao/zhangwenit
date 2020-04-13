package com.zhangwen.learn.zhangwenit.concurrent.thread.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description 默认线程池实现
 * @Author ZWen
 * @Date 2020/4/13 9:37 PM
 * @Version 1.0
 **/
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 最大工作者数量
     */
    private static final int MAX_WORKER_NUM = 10;
    /**
     * 最小工作者数量
     */
    private static final int MIN_WORKER_NUM = 1;
    /**
     * 默认工作者数量
     */
    private static final int DEFAULT_WORKER_NUM = 5;

    /**
     * 工作者数量
     */
    private int workerCount = DEFAULT_WORKER_NUM;

    /**
     * 任务队列
     */
    private LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者同步队列
     */
    private List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 线程编号
     */
    private AtomicLong threadSid = new AtomicLong(1);

    public DefaultThreadPool() {
        initWorkers(DEFAULT_WORKER_NUM);
    }

    public DefaultThreadPool(int num) {
        this.workerCount = num > MAX_WORKER_NUM ? MAX_WORKER_NUM : num < MIN_WORKER_NUM ? MIN_WORKER_NUM : num;
        initWorkers(workerCount);
    }

    /**
     * 执行任务
     *
     * @param job
     */
    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    /**
     * 添加工作者
     *
     * @param num
     * @return
     */
    @Override
    public int addWorkers(int num) {
        synchronized (jobs) {
            if (num > MAX_WORKER_NUM - workerCount) {
                num = MAX_WORKER_NUM - workerCount;
            }
            initWorkers(num);
            workerCount += num;
            return num;
        }
    }

    /**
     * 移除工作者
     *
     * @param num
     * @returns
     */
    @Override
    public int removeWorkers(int num) {
        synchronized (jobs) {
            if (num > workerCount) {
                throw new IllegalArgumentException("beyond workerCount");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            workerCount -= num;
        }
        return 0;
    }

    /**
     * 停止线程池
     */
    @Override
    public void shutdown() {
        if (!workers.isEmpty()) {
            for (Worker worker : workers) {
                worker.shutdown();
            }
        }
    }

    /**
     * 获取当前任务队列数量
     *
     * @return
     */
    @Override
    public int getJobSize() {
        return jobs.size();
    }

    /**
     * 初始化并启动工作者线程
     *
     * @param num
     * @return
     */
    private int initWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "worker-thread-" + threadSid.getAndIncrement());
            thread.start();
        }
        return num;
    }


    class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知外部对workerThread的中断操作
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {

                    }
                }
            }
        }

        private void shutdown() {
            running = false;
        }
    }
}