package com.zhangwen.learn.zhangwenit.api.merchant.dto;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/24 3:32 PM
 * @Version 1.0
 **/
public class DemoDto {

    private String name;
    private Integer sex;
    private Boolean flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "DemoDto{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", flag=" + flag +
                '}';
    }
}