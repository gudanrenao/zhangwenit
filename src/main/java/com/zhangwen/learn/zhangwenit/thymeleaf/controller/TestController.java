package com.zhangwen.learn.zhangwenit.thymeleaf.controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 测试
 *
 * @author zhangwen at 2018-06-15 22:30
 **/
@RestController
public class TestController {

    @GetMapping("/template/{userName}")
    public String template(@PathVariable String userName, Model model) {

        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(null);
        model.addAttribute("id", "1212");
        model.addAttribute("userName", userName);
        return "/email/template";
    }

}
