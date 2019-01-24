package com.zhangwen.learn.zhangwenit;

import com.google.common.collect.Lists;
import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Description 临时
 * @Author ZWen
 * @Date 2019/1/9 5:11 PM
 * @Version 1.0
 **/
public class Test {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();


        long start = System.currentTimeMillis();
        List<Integer> voList = Lists.newArrayList(1,2,3,4,5);
        if(voList.size() > 0){
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder().namingPattern("dispense-pool-%d").daemon(true).build());
            final CountDownLatch countDownLatch = new CountDownLatch(voList.size());
            for (Integer dispenseVo : voList) {
                // 获取病区
                executor.execute(() -> {

                        countDownLatch.countDown();

                });
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("foreach expend time：" + (end - start));

    }
}