package com.zhangwen.learn.zhangwenit.annotation.config;

import com.zhangwen.learn.zhangwenit.annotation.annotation.ExecutionTimeTest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 接口执行时间统计切面
 *
 * @author zhangwen at 2018-12-05 23:20
 **/
@Aspect
@Component
public class ExecutionTimeAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Pointcut("@annotation( com.zhangwen.learn.zhangwenit.annotation.annotation.ExecutionTimeTest)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        threadLocal.set(System.currentTimeMillis());
        LOGGER.info("执行方法：{},开始时间：{}", method.getAnnotation(ExecutionTimeTest.class).value(), System.currentTimeMillis());
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("执行方法：{},结束时间：{},消耗时间：{}毫秒", method.getAnnotation(ExecutionTimeTest.class).value(), System.currentTimeMillis(), System.currentTimeMillis() - threadLocal.get());
        threadLocal.remove();
    }
}
