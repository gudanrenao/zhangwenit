package com.zhangwen.learn.zhangwenit.basic.io.dto;

import java.io.Serializable;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/25 10:10 AM
 * @Version 1.0
 **/
public class Student implements Serializable {

    private String name;
    /**
     * 被transient修饰的变量不会被序列化(持久化)
     */
    private String headImg;
    private transient int sex;

    public Student() {
    }

    public Student(String name, String headImg, int sex) {
        this.name = name;
        this.headImg = headImg;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                ", sex=" + sex +
                '}';
    }
}