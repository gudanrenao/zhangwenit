package com.zhangwen.learn.zhangwenit.designmodel.visitor;

/**
 * @Description 具体元素
 * @Author ZWen
 * @Date 2020/4/7 6:38 PM
 * @Version 1.0
 **/
public class ConcreteElementA implements Element {

    /**
     * 以访问者为参数
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        additionOperate(visitor);
        visitor.visitConcreteElementA(this);
    }

    private void additionOperate(Visitor visitor){
        System.out.println("ConcreteElementA addition operate visitor type is : " + visitor.getClass().toString());
    }
}