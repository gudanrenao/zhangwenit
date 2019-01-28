package com.zhangwen.learn.zhangwenit.synchronous.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class SynchronousTestServiceTest {

    @Autowired
    private SynchronousTestService synchronousTestService;

    @Test
    public void insertTest() throws InterruptedException {
        final String name = "zw";
        ExecutorService executorService = new ScheduledThreadPoolExecutor(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    synchronousTestService.insertTest(name);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("===========SUCCESS==============");
    }
}