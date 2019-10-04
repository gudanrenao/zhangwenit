package com.zhangwen.learn.zhangwenit.designmodel.adapter.first;

/**
 * @Description 通过在内部包装一个Adaptee对象，将源接口转换为目标接口
 * @Author ZWen
 * @Date 2019/10/4 11:52 AM
 * @Version 1.0
 **/
public class Adapter extends Target {

    private final Adaptee adaptee = new Adaptee();

    @Override
    public void hello() {
        adaptee.trueRequest();
    }
}