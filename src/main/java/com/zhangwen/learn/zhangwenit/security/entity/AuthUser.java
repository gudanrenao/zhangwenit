package com.zhangwen.learn.zhangwenit.security.entity;

import javax.persistence.*;

/**
 * @Description 登录用户表
 * @Author ZWen
 * @Date 2018/12/5 3:01 PM
 * @Version 1.0
 **/
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String password;

    /**
     * 账户状态，1=可用，0=禁用
     */
    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}