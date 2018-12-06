package com.zhangwen.learn.zhangwenit.security.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description 用户角色关系表
 * @Author ZWen
 * @Date 2018/12/5 3:46 PM
 * @Version 1.0
 **/
@Entity
public class AuthUserRole {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date createdAt = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}