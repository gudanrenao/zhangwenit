package com.zhangwen.learn.zhangwenit.asynchronous;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousTest {

    private final Object lock = new Object();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private volatile int num = 0;


    @Before
    public void setUp() throws Exception {
        executorService.execute(() -> {
            synchronized (lock) {
                try {
                    while (num != 200) {
                        Thread.sleep(500);
                        num += 50;
                        System.out.println("num is " + num);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("num is 200 notifyAll...");
                    lock.notifyAll();
                }
            }
        });
    }

    @Test
    public void myTest() throws InterruptedException {
        Thread.sleep(200);
        synchronized (lock) {
            assert num == 200;
        }
    }
}