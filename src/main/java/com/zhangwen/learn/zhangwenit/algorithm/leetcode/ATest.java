package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

import java.util.Map;
import java.util.Random;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/6/2 5:25 PM
 * @Version 1.0
 **/
public class ATest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Random random = new Random(123456789);
            for (int j = 0; j < 10; j++) {
                System.out.print(random.nextInt(100) + "    ");
            }
            System.out.println("===============================================");
        }

    }
}