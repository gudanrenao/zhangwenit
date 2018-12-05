package com.zhangwen.learn.zhangwenit.annotation.controller;

import com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试MyTest注解
 *
 * @author zhangwen at 2018-12-05 21:55
 **/
@MyTest("测试 controller")
@RestController
@RequestMapping("annotation")
public class MyTestController {

    @MyTest("测试 myTest method")
    @GetMapping("myTest")
    public String myTest() {
        System.out.println("myTest method execute");
        return "SUCCESS";
    }

    @GetMapping("myTestNo")
    public String myTestNoAnnotation() {
        System.out.println("myTestNoAnnotation method execute");
        return "SUCCESS";
    }
}
