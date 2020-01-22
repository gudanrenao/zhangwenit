package com.zhangwen.learn.zhangwenit.transaction.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Description user
 * @Author ZWen
 * @Date 2019/1/11 2:07 PM
 * @Version 1.0
 **/
@Data
@Entity
public class TestTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}