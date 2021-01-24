package com.zhangwen.learn.zhangwenit.http.method.trace;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/12/24 8:38 PM
 * @Version 1.0
 **/
public class TraceDemo {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        String url = "http://amt.livetest.zkjin.com/web/backstage";
        final ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.TRACE, null, Object.class);

        System.out.println(responseEntity);
    }

}