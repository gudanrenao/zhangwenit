//package com.zhangwen.learn.zhangwenit.rest.restTemplate.config;
//
//import com.zhangwen.learn.zhangwenit.rest.restTemplate.interceptor.CustomRestTemplateInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Description
// * @Author ZWen
// * @Date 2019/3/14 6:41 PM
// * @Version 1.0
// **/
//@Configuration
//public class RestTemplateConfig {
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//        interceptors.add(new CustomRestTemplateInterceptor());
//        interceptors.addAll(restTemplate.getInterceptors());
//        restTemplate.setInterceptors(interceptors);
//        return restTemplate;
//    }
//}