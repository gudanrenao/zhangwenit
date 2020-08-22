package com.zhangwen.learn.zhangwenit.netty.groupchat2;

import java.io.Serializable;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/13 8:55 PM
 * @Version 1.0
 **/
public class UserDto implements Serializable {
    static final long serialVersionUID = 42L;

    private String userName;
    private String msg;

    public UserDto() {
    }

    public UserDto(String userName, String msg) {
        this.userName = userName;
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}