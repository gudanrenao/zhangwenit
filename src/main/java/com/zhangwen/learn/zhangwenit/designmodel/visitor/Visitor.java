package com.zhangwen.learn.zhangwenit.designmodel.visitor;

/**
 * @Description 访问者 : 为每个具体的元素声明一个操作 操作一般经常变动
 * @Author ZWen
 * @Date 2020/4/7 6:36 PM
 * @Version 1.0
 **/
public interface Visitor {

    void visitConcreteElementA(ConcreteElementA concreteElementA);

    void visitConcreteElementB(ConcreteElementB concreteElementB);
}
