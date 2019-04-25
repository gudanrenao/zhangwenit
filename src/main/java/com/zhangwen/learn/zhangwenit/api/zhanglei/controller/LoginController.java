package com.zhangwen.learn.zhangwenit.api.zhanglei.controller;

import com.zhangwen.learn.zhangwenit.common.dto.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登录
 * @Author ZWen
 * @Date 2019/4/25 6:02 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/zhanglei/login")
@Api(value = "登录相关接口", tags = "LOGIN", description = "登录/注销等", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @GetMapping("/loginIn")
    public ResponseVO loginIn() {
        return ResponseVO.buildSuccess();
    }

}