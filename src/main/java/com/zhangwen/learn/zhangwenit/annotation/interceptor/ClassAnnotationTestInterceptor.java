package com.zhangwen.learn.zhangwenit.annotation.interceptor;

import com.zhangwen.learn.zhangwenit.annotation.annotation.ClassTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 自定义类注解拦截器测试
 * @Author ZWen
 * @Date 2018/12/6 11:36 AM
 * @Version 1.0
 **/
@Component
public class ClassAnnotationTestInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClassAnnotationTestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> aClass = handlerMethod.getBeanType();
            ClassTest annotation = aClass.getAnnotation(ClassTest.class);
            if (annotation != null) {
                LOGGER.error("ClassAnnotationTestFilter run annotation value is : {}", annotation.value());
            }
        }

        return true;
    }
}