package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

/**
 * @Description 球员
 * @Author ZWen
 * @Date 2019/10/4 12:02 PM
 * @Version 1.0
 **/
public abstract class Player {

    private final String name;

    protected Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //进攻
    public abstract void attack();
    //防守
    public abstract void defense();
}