package com.zhangwen.learn.zhangwenit.jvm.javap;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 语法糖：自动装箱、拆箱、循环遍历
 * @Author ZWen
 * @Date 2020/4/7 2:20 PM
 * @Version 1.0
 **/
public class AutoBoxCase {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        
        int sum = 0;

        for (Integer integer : integerList) {
            sum += integer;
        }

        System.out.println(sum);
    }
}