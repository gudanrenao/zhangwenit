package com.zhangwen.learn.zhangwenit.basic.reuse_class;

import java.util.Random;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/30 2:07 PM
 * @Version 1.0
 **/
public class DemoClass {

    public final int age = new Random().nextInt(10000);

    public final int sex;

    public DemoClass(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "DemoClass{" +
                "age=" + age +
                '}';
    }
}