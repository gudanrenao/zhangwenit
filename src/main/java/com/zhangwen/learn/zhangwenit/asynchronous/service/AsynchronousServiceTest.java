package com.zhangwen.learn.zhangwenit.asynchronous.service;

import com.zhangwen.learn.zhangwenit.asynchronous.util.CommonConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description 测试
 * @Author ZWen
 * @Date 2018/12/6 4:45 PM
 * @Version 1.0
 **/
@Service
public class AsynchronousServiceTest {

    @Async
    public void startRun(Integer orgId) {
        int status = 0;
        int time = new Random().nextInt(1000 * 20);
        try {
            Thread.sleep(time);
            status = new Random().nextInt(10);
            if (status % 2 == 0) {
                int s = 10 / 0;
            }
        } catch (Exception e) {
            status = -2;
            e.printStackTrace();
        } finally {
            CommonConstant.EXEC_STATUS_MAP.put(orgId, status);
        }
    }
}