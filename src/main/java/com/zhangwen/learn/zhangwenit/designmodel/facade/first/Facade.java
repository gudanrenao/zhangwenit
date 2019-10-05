package com.zhangwen.learn.zhangwenit.designmodel.facade.first;

/**
 * @Description 门面模式中的外观类
 * @Author ZWen
 * @Date 2019/10/5 11:03 AM
 * @Version 1.0
 **/
public class Facade {

    private SystemOne systemOne;
    private SystemTwo systemTwo;
    private SystemThree systemThree;

    public Facade() {
        this.systemOne = new SystemOne();
        this.systemTwo = new SystemTwo();
        this.systemThree = new SystemThree();
    }

    public void methodA(){
        systemOne.one();
        systemTwo.two();
    }

    public void methodB(){
        systemTwo.two();
        systemThree.three();
    }
}