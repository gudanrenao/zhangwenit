package com.zhangwen.learn.zhangwenit.basic.java.lang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/9 6:13 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/runtime")
public class RuntimeTest {

    /**
     * 获取当前设备的CPU个数
     */
    @GetMapping("/availableProcessors")
    public Map availableProcessors() {
        Map<String, Object> result = new HashMap<>();
        result.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        return result;
    }
}