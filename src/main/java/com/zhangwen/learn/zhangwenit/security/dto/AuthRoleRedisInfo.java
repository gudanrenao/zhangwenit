package com.zhangwen.learn.zhangwenit.security.dto;

import java.io.Serializable;

/**
 * @Description redis
 * @Author ZWen
 * @Date 2018/12/5 4:01 PM
 * @Version 1.0
 **/
public class AuthRoleRedisInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;

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
}