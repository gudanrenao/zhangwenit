package com.zhangwen.learn.zhangwenit.basic.java.time;

import java.time.Duration;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/20 11:24 PM
 * @Version 1.0
 **/
public class DurationCase {

    public static void main(String[] args) {
        System.out.println(Duration.ofDays(30).toMillis());
    }
}