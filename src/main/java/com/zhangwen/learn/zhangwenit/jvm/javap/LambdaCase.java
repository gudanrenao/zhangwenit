package com.zhangwen.learn.zhangwenit.jvm.javap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 语法糖：lambda
 * @Author ZWen
 * @Date 2020/4/7 3:01 PM
 * @Version 1.0
 **/
public class LambdaCase {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> stringList = list.stream().map(e -> e + "123").collect(Collectors.toList());
        System.out.println(list);
        System.out.println(stringList);
    }
}