package com.zhangwen.learn.zhangwenit.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description user
 * @Author ZWen
 * @Date 2019/1/11 2:07 PM
 * @Version 1.0
 **/
@Entity
public class TestTransaction {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}