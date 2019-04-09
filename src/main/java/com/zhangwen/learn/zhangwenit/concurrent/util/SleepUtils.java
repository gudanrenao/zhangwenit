package com.zhangwen.learn.zhangwenit.concurrent.util;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/4 10:18 AM
 * @Version 1.0
 **/
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}