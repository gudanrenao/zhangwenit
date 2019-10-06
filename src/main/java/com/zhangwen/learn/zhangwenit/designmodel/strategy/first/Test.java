package com.zhangwen.learn.zhangwenit.designmodel.strategy.first;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/10/6 4:33 PM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        //相比简单工厂模式的first代码，客户端只需要知道一个StrategyContext类就可以了
        StrategyContext strategyContext1 = new StrategyContext("PLUS");
        strategyContext1.selectRule();

        StrategyContext strategyContext2 = new StrategyContext("SUB");
        strategyContext2.selectRule();
    }
}