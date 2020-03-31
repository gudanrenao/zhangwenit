package com.zhangwen.learn.zhangwenit.jvm;

/**
 * @Description -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @Author ZWen
 * @Date 2020/3/27 9:42 AM
 * @Version 1.0
 **/
public class TestAllocation {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[4 * _1M];//这里会发生 Minor GC
        System.out.println("123456");
    }
}