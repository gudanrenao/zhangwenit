package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 前锋
 * @Author ZWen
 * @Date 2019/10/4 12:03 PM
 * @Version 1.0
 **/
public class Forward extends Player {

    private final Logger log = LoggerFactory.getLogger(Forward.class);

    protected Forward(String name) {
        super(name);
    }

    @Override
    public void attack() {
        log.info("forward player {} attack", super.getName());
    }

    @Override
    public void defense() {
        log.info("forward player {} defense", super.getName());
    }
}