package com.zhangwen.learn.zhangwenit.util;

/**
 * @Description 控制台输出
 * @Author ZWen
 * @Date 2019/3/2 4:21 PM
 * @Version 1.0
 **/
public final class PrintUtils {

    public static void println(Object msg) {
        System.out.printf("current thread[%s]  msg:[%s] \n", Thread.currentThread().getName(), String.valueOf(msg));
    }
}