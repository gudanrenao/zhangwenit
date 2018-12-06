package com.zhangwen.learn.zhangwenit.annotation.config;

import com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ceshi
 *
 * @author zhangwen at 2018-12-05 22:33
 **/
@Aspect
@Component
public class MyTestAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyTestAspect.class);

    @Pointcut("@annotation(com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyTest annotation = method.getAnnotation(MyTest.class);
        LOGGER.warn("MyTest before : {}", annotation.value());
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        LOGGER.warn("MyTest around 通知之开始");
        Object retmsg = null;
        try {
            retmsg = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        LOGGER.warn("MyTest around 通知之结束");
        return retmsg;
    }
}
