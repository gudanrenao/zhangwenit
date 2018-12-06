package com.zhangwen.learn.zhangwenit.security.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description 角色
 * @Author ZWen
 * @Date 2018/12/5 3:13 PM
 * @Version 1.0
 **/
@Entity
public class AuthRole {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}