package com.zhangwen.learn.zhangwenit.annotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试MyTest注解
 *
 * @author zhangwen at 2018-12-05 21:55
 **/
@RestController
@RequestMapping("annotation2")
public class MyTest2Controller {

    @GetMapping("myTest2")
    public String myTest() {
        System.out.println("myTest2 method execute");
        return "SUCCESS";
    }

}
