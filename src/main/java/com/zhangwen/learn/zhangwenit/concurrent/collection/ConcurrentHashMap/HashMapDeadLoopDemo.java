package com.zhangwen.learn.zhangwenit.concurrent.collection.ConcurrentHashMap;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

/**
 * @Description HashMap并发put时容易死循环(形成了环形Entry)
 * @Author ZWen
 * @Date 2020/4/15 6:30 PM
 * @Version 1.0
 **/
public class HashMapDeadLoopDemo {

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(4);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(),"");
                }).start();
            }
        });
        t.start();
        t.join();
        System.out.println("1234");

        String s = "null";
        Optional<Object> o = Optional.ofNullable(s);
        if(o.isPresent()){
            System.out.println(o.get());
        }
    }
}