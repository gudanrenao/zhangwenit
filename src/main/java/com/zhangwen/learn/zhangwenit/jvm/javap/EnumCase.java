package com.zhangwen.learn.zhangwenit.jvm.javap;

/**
 * @Description 语法糖：枚举类 TODO:?
 * @Author ZWen
 * @Date 2020/4/7 2:46 PM
 * @Version 1.0
 **/
public enum EnumCase {

    CASE1("1"), CASE2("2");

    final String value;

    EnumCase(String value) {
        this.value = value;
    }

    public static void main(String[] args) {

    }
}
