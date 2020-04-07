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

        for (int i = 0; i < integerList.size(); i++) {
            sum += integerList.get(i);
        }
        //下面这个实际使用的 iterator迭代器
        for (Integer integer : integerList) {
            sum += integer;
        }


        System.out.println(sum);


    }

    /**
     * 包装类的“==”运算在不遇到算术运算的情况下不会自动拆箱，以及它们equals()方法不处理数据转型的关系
     */
    public static void test(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;

        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true

        //  public boolean equals(Object obj) {
        //        if (obj instanceof Long) {
        //            return value == ((Long)obj).longValue();
        //        }
        //        return false;
        //    }
        System.out.println(g.equals(a + b));//false
    }
}