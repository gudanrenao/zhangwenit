package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 请求
 * @Author ZWen
 * @Date 2019/1/19 6:51 PM
 * @Version 1.0
 **/
public class CustomReq {

    private String name;

    private int age;

    public CustomReq(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CustomReq{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}