package com.zhangwen.learn.zhangwenit.annotation.config;

import com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("@annotation(com.zhangwen.learn.zhangwenit.annotation.annotation.MyTest)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyTest annotation = method.getAnnotation(MyTest.class);
        System.out.println("before 打印：" + annotation.value() + " 开始前");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("around 通知之开始");
        Object retmsg = null;
        try {
            retmsg = joinPoint.proceed();
            System.err.println("++++++++" + retmsg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("around 通知之结束");
        return retmsg;
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after 方法执行后");
    }
}
