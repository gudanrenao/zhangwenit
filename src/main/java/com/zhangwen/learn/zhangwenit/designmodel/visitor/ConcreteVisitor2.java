package com.zhangwen.learn.zhangwenit.designmodel.visitor;

/**
 * @Description 操作2
 * @Author ZWen
 * @Date 2020/4/7 6:49 PM
 * @Version 1.0
 **/
public class ConcreteVisitor2 implements Visitor {

    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println("ConcreteElementA 被 ConcreteVisitor2操作 执行");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println("ConcreteElementB 被 ConcreteVisitor2操作 执行");
    }
}