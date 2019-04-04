package com.zhangwen.learn.zhangwenit.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/3 11:41 PM
 * @Version 1.0
 **/
public class ThreadFirstDemo {

    /**
     * [ 1 ] main   main线程，用户程序入口
     * [ 2 ] Reference Handler  清除Reference的线程
     * [ 10 ] Common-Cleaner
     * [ 11 ] Monitor Ctrl-Break
     * [ 4 ] Signal Dispatcher  分发处理发送给JVM信号的线程
     * [ 3 ] Finalizer  调用对象finalizer方法的线程
     * @param args
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[ " + threadInfo.getThreadId() + " ] " + threadInfo.getThreadName());
        }
    }
}