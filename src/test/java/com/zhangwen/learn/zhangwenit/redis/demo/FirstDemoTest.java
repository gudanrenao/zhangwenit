package com.zhangwen.learn.zhangwenit.redis.demo;

import com.zhangwen.learn.zhangwenit.ZhangwenitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest(classes = ZhangwenitApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class FirstDemoTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HashOperations<String,String,String> hashOperations;

    @Test
    public void test(){
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void hashSet(){
        Boolean b1 = stringRedisTemplate.opsForHash().putIfAbsent("map1", "key1", "value1");
        stringRedisTemplate.opsForHash().putIfAbsent("map1","key2","value2");
        stringRedisTemplate.opsForHash().putIfAbsent("map1","key3","value3");
        Boolean b2 = stringRedisTemplate.opsForHash().putIfAbsent("map1", "key1", "value100");
        System.out.println(b1 + "===" + b2);

        Map<Object, Object> map1 = stringRedisTemplate.opsForHash().entries("map1");
        System.out.println(map1);
    }

    @Test
    public void hashOperationsGet(){
        Map<String, String> map1 = hashOperations.entries("map1");
        System.out.println(map1);
    }
}
