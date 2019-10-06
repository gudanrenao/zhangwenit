package com.zhangwen.learn.zhangwenit.designmodel.strategy.first;

/**
 * @Description 加法
 * @Author ZWen
 * @Date 2019/10/6 4:05 PM
 * @Version 1.0
 **/
public class Plus implements Compute {

    @Override
    public void rule() {
        System.out.println("加法");
    }
}