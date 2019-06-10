package com.zhangwen.learn.zhangwenit.basic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/4 10:20 AM
 * @Version 1.0
 **/
public class FirstDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger("123");

    public static void main(String[] args) {
        try {
            throw new RuntimeException("nihao");
        }catch (RuntimeException e){
            e.printStackTrace(System.err);
        }
    }
}