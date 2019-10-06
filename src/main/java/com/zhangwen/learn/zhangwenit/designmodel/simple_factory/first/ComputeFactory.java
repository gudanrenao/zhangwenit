package com.zhangwen.learn.zhangwenit.designmodel.simple_factory.first;

/**
 * @Description 计算工厂
 * @Author ZWen
 * @Date 2019/10/6 4:08 PM
 * @Version 1.0
 **/
public class ComputeFactory {

    enum Item {
        SUB, PLUS
    }

    public static Compute getCompute(Item item) {
        switch (item) {
            case SUB:
                return new Sub();
            case PLUS:
                return new Plus();
            default:
                throw new RuntimeException("不支持当前类型的计算");
        }
    }
}