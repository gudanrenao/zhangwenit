package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 后卫
 * @Author ZWen
 * @Date 2019/10/4 12:03 PM
 * @Version 1.0
 **/
public class Guard extends Player {

    private final Logger log = LoggerFactory.getLogger(Guard.class);

    protected Guard(String name) {
        super(name);
    }

    @Override
    public void attack() {
        log.info("guard player {} attack", super.getName());
    }

    @Override
    public void defense() {
        log.info("guard player {} defense", super.getName());
    }
}