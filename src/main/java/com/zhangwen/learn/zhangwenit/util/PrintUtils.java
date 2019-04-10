package com.zhangwen.learn.zhangwenit.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Description 控制台输出
 * @Author ZWen
 * @Date 2019/3/2 4:21 PM
 * @Version 1.0
 **/
public final class PrintUtils {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static void println(Object msg) {
        System.out.printf("current thread[%s]  msg:[%s] \n", Thread.currentThread().getName(), String.valueOf(msg));
    }

    public static String currTime() {
        return DateFormatUtils.format(new Date(), pattern);
    }
}