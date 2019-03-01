package com.zhangwen.learn.zhangwenit.designmodel.observer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 观察者模式
 * @Author ZWen
 * @Date 2019/2/22 1:01 PM
 * @Version 1.0
 **/
public class ObserverTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println(event.getSource());
                System.out.println(event.getApplicationContext());
            }
        });
        applicationContext.refresh();
        applicationContext.publishEvent(new MyApplicationEvent(applicationContext,"nihao"));
    }

    private static class MyApplicationEvent extends ApplicationEvent {

        private final ApplicationContext applicationContext;
        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationEvent(ApplicationContext applicationContext,Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }
}