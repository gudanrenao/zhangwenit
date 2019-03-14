package com.zhangwen.learn.zhangwenit.rest.restTemplate.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;

/**
 * @Description 自定义 restTemplate 拦截器
 * @Author ZWen
 * @Date 2019/3/14 6:45 PM
 * @Version 1.0
 **/
public class CustomRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomRestTemplateInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI uri = request.getURI();
        logger.error("CustomRestTemplateInterceptor uri[{}]", uri.toString());
        return execution.execute(request, body);
    }
}