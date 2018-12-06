package com.zhangwen.learn.zhangwenit.asynchronous.controller;

import com.zhangwen.learn.zhangwenit.asynchronous.service.AsynchronousServiceTest;
import com.zhangwen.learn.zhangwenit.asynchronous.util.CommonConstant;
import com.zhangwen.learn.zhangwenit.common.dto.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试
 * @Author ZWen
 * @Date 2018/12/6 4:31 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("asynchronous")
public class TestAsynchronousController {

    private final AsynchronousServiceTest asynchronousServiceTest;

    public TestAsynchronousController(AsynchronousServiceTest asynchronousServiceTest) {
        this.asynchronousServiceTest = asynchronousServiceTest;
    }

    /**
     * 测试异步执行一个耗时的操作，第二次该用户的该操作会等待上一次已执行完才会异步执行，如果未执行完，直接响应
     *
     * @param orgId
     * @return
     */
    @GetMapping("execTest/{orgId}")
    public ResponseVO execTest(@PathVariable Integer orgId) {
        Integer status = CommonConstant.EXEC_STATUS_MAP.get(orgId);
        String msg = "本次同步开始...";
        if (status != null && status == -1) {
            return ResponseVO.buildSuccess("同步任务还在进行中...");
        }
        if (status != null && status == -2) {
            msg = "上次同步任务已失败...,本次同步开始...";
        }
        if (status != null && status > 0) {
            msg = "上次同步任务成功数量为" + status + "个,本次同步开始...";
        }
        //无或-2，执行任务
        CommonConstant.EXEC_STATUS_MAP.put(orgId, -1);
        asynchronousServiceTest.startRun(orgId);
        return ResponseVO.buildSuccess(msg);
    }
}