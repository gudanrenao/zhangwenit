package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 中国中锋
 * @Author ZWen
 * @Date 2019/10/4 12:11 PM
 * @Version 1.0
 **/
public class ChinaCenter {

    private final Logger log = LoggerFactory.getLogger(ChinaCenter.class);

    private final String name;

    public ChinaCenter(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void 进攻() {
        log.info("中国中锋 {} 进攻呀！！！", this.getName());
    }

    public void 防守() {
        log.info("中国中锋 {} 防守呀！！！", this.getName());
    }
}