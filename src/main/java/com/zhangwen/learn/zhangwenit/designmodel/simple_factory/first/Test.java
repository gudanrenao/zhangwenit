package com.zhangwen.learn.zhangwenit.designmodel.simple_factory.first;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/10/6 4:14 PM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        //相比策略模式的first代码，客户端需要知道两个类，一个ComputeFactory,一个Compute
        Compute plus = ComputeFactory.getCompute(ComputeFactory.Item.PLUS);
        plus.rule();

        Compute sub = ComputeFactory.getCompute(ComputeFactory.Item.SUB);
        sub.rule();
    }
}