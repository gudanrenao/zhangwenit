package com.zhangwen.learn.zhangwenit.designmodel.visitor;

/**
 * @Description 访问者模式：适用于系统有比较稳定的数据结构，又有易于变化的算法
 * @Author ZWen
 * @Date 2020/4/7 6:55 PM
 * @Version 1.0
 **/
public class Case {

    /**
     * 访问者模式的优点：将数据结构和作用于结构之上的操作解耦，增加新的操作方便
     * 访问者模式的缺点：增加新的数据结构很困难
     * 这里使用到了：双分派技术
     *
     * @param args
     */
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new ConcreteElementA());
        objectStructure.attach(new ConcreteElementB());

        Visitor visitor1 = new ConcreteVisitor1();
        objectStructure.accept(visitor1);

        Visitor visitor2 = new ConcreteVisitor2();
        objectStructure.accept(visitor2);
    }
}