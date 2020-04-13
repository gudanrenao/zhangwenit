package com.zhangwen.learn.zhangwenit.concurrent.thread.threadpool;

/**
 * @Description  java并发编程的艺术 4.4.3
 * @Author ZWen
 * @Date 2020/4/13 4:11 PM
 * @Version 1.0
 **/
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行任务
     *
     * @param job
     */
    void execute(Job job);

    /**
     * 添加工作者
     *
     * @param num
     * @return
     */
    int addWorkers(int num);

    /**
     * 移除工作者
     *
     * @param num
     * @returns
     */
    int removeWorkers(int num);

    /**
     * 停止线程池
     */
    void shutdown();

    /**
     * 获取当前任务队列数量
     *
     * @return
     */
    int getJobSize();
}
