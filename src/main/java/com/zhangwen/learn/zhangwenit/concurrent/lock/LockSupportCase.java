package com.zhangwen.learn.zhangwenit.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/15 4:13 PM
 * @Version 1.0
 **/
public class LockSupportCase {

    private String demo = "123456";

    public static void main(String[] args) {
        new Thread(() -> new LockSupportCase().pack()).start();
        new Thread(() -> new LockSupportCase().packThis()).start();
    }

    private void pack() {
        LockSupport.park();
    }

    private void packThis() {
        //这种会便于调试，线程dump有标记
        LockSupport.park(this);
    }
}