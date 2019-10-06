package com.zhangwen.learn.zhangwenit.designmodel.strategy.first;

/**
 * @Description 减法
 * @Author ZWen
 * @Date 2019/10/6 4:07 PM
 * @Version 1.0
 **/
public class Sub implements Compute {

    @Override
    public void rule() {
        System.out.println("减法");
    }
}