package com.zhangwen.learn.zhangwenit.java8.newFeature;

import com.google.common.collect.Lists;
import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description 并行执行流 ForkJoinPool
 * @Author ZWen
 * @Date 2019/1/28 3:22 PM
 * @Version 1.0
 **/
public class ParallelStreamTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        int sum = list.parallelStream().mapToInt(e -> e).sum();
//        System.out.println("sum is : " + sum);
        User u = new User();
        u.setId(0L);
        list.parallelStream().forEach(e -> u.setId(u.getId() + e));
        System.out.println("sum is : " + u.getId());


        //list2元素可能少于list的元素数量
        //原因：list.add执行流程：
        // 1.判断size是否=内部数组长度，如果相等，拷贝数组到一个新数组，新数组的长度为size+1
        // 2.将待添加元素赋值给数组[size]
        // 3.size++
        //线程都是分片执行，当A线程执行完2时，B线程抢到资源，开始执行，那么将会把A add的值覆盖掉，
        // 同时，size = size + 1 不是原子性的，在A将size+1后，B可能拿到的是之前的size，A和B都加1后，实际可能只是加了1，而不是+2
        for (int i = 0; i < 5; i++) {
            List<Integer> list2 = new ArrayList<>();
            list.parallelStream().forEach(list2::add);
            System.out.println(list2 + "----" + list2.size());
        }

        Stream<Integer> stream = Stream.of(1,2,3,4);
        stream.forEach(System.out::println);

        Stream<User> stream1 = Stream.of(null,null);

    }
}