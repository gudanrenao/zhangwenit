package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 中锋
 * @Author ZWen
 * @Date 2019/10/4 12:03 PM
 * @Version 1.0
 **/
public class Center extends Player {

    private final Logger log = LoggerFactory.getLogger(Center.class);

    protected Center(String name) {
        super(name);
    }

    @Override
    public void attack() {
        log.info("center player {} attack", super.getName());
    }

    @Override
    public void defense() {
        log.info("center player {} defense", super.getName());
    }
}