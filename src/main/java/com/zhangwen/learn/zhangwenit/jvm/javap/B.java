package com.zhangwen.learn.zhangwenit.jvm.javap;

/**
 * @Description
 * @Author ZWen
 * @Date 2021/1/24 2:14 PM
 * @Version 1.0
 **/
public class B {

    private int a = 1234;

    static long C = 1111;

    public long test(long num) {
        long ret = this.a + num + C;
        return ret;
    }
}