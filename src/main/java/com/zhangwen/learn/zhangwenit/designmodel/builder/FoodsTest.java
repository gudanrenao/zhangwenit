package com.zhangwen.learn.zhangwenit.designmodel.builder;

/**
 * @Description 测试建造者模式
 * @Author ZWen
 * @Date 2019/1/24 2:04 PM
 * @Version 1.0
 **/
public class FoodsTest {

    private static FoodsBuilder foodsBuilder = new FoodsBuilder();

    public static void main(String[] args) {

        Foods foods1 = foodsBuilder.chickenAndCoffee();
        foods1.print();

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------");

        Foods foods2 = foodsBuilder.fishAndPeer();
        foods2.print();
    }
}