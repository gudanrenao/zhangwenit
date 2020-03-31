package com.zhangwen.learn.zhangwenit.jvm.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/3/28 4:24 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("jvm")
public class StackTraceController {

    @GetMapping("getAllStackTraces")
    public Map getAllStackTraces() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread thread = entry.getKey();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            System.out.println("\n线程：" + thread.getName() + "\n");
            StackTraceElement[] traceElements = entry.getValue();
            for (StackTraceElement traceElement : traceElements) {
                System.out.println("\t" + traceElement + "\n");
            }
        }

        return allStackTraces;
    }
}