package com.zhangwen.learn.zhangwenit.annotation.controller;

import com.zhangwen.learn.zhangwenit.annotation.annotation.ClassTest;
import com.zhangwen.learn.zhangwenit.annotation.annotation.ExecutionTimeTest;
import com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 测试MyTest注解
 *
 * @author zhangwen at 2018-12-05 21:55
 **/
@ClassTest("测试MyTestController")
@RestController
@RequestMapping("annotation")
public class MyTestController {

    @MyTest("测试myTest-method")
    @GetMapping("myTest")
    public String myTest() {
        System.out.println("myTest method execute");
        return "SUCCESS";
    }

    @ExecutionTimeTest("timeTest")
    @GetMapping("timeTest")
    public String timeTest() throws InterruptedException {
        //随机延迟
        int i = new Random().nextInt(1000);
        System.out.println("timeTest method execute,sleep:" + i);
        Thread.sleep(i);
        return "SUCCESS";
    }

}
