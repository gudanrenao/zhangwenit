package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 响应
 * @Author ZWen
 * @Date 2019/1/19 6:51 PM
 * @Version 1.0
 **/
public class CustomResp {

    private int code;

    private String name;

    private int age;

    public CustomResp(int code) {
        this.code = code;
    }

    public CustomResp() {
    }

    public CustomResp(int code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public CustomResp(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        return "CustomResp{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}