package com.zhangwen.learn.zhangwenit.designmodel.simple_factory.first;

/**
 * @Description 计算
 * @Author ZWen
 * @Date 2019/10/6 3:41 PM
 * @Version 1.0
 **/
public interface Compute {

    void rule();

    ComputeFactory.Item support();
}
