package com.zhangwen.learn.zhangwenit.rest.restTemplate;

import com.zhangwen.learn.zhangwenit.rest.restTemplate.interceptor.CustomRestTemplateInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/3/14 6:52 PM
 * @Version 1.0
 **/
public class Demo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new CustomRestTemplateInterceptor());
        interceptors.addAll(restTemplate.getInterceptors());
        restTemplate.setInterceptors(interceptors);
        Map<String,Object> params = new HashMap<>(4);
        params.put("name","张文");
        params.put("age",18);
        params.put("birthday","1992-01-07");
        String result = restTemplate.getForObject("http://localhost:9011/user/findByName?name={name}&age={age}", String.class,params);

        System.out.println(result);
    }
}