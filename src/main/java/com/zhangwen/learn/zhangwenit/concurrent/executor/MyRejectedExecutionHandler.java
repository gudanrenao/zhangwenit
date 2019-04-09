package com.zhangwen.learn.zhangwenit.concurrent.executor;

import com.zhangwen.learn.zhangwenit.concurrent.executor.ThreadPoolExecutor.MyThreadPoolExecutor;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/9 1:53 PM
 * @Version 1.0
 **/
public interface MyRejectedExecutionHandler {

    void rejectedExecution(Runnable r, MyThreadPoolExecutor executor);
}
