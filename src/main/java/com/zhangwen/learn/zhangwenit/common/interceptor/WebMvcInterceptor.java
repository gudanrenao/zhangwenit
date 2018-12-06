package com.zhangwen.learn.zhangwenit.common.interceptor;

import com.zhangwen.learn.zhangwenit.annotation.interceptor.ClassAnnotationTestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description 拦截器配置
 * @Author ZWen
 * @Date 2018/12/6 11:56 AM
 * @Version 1.0
 **/
@Configuration
public class WebMvcInterceptor extends WebMvcConfigurationSupport {

    private final ClassAnnotationTestInterceptor classAnnotationTestFilter;

    public WebMvcInterceptor(ClassAnnotationTestInterceptor classAnnotationTestFilter) {
        this.classAnnotationTestFilter = classAnnotationTestFilter;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(classAnnotationTestFilter);
    }
}