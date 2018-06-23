package com.zhangwen.learn.zhangwenit.algorithm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 生成随机数组&计算算法消耗时间工具类
 *
 * @author zhangwen at 2018-06-17 0:24
 **/
public class SortTestHelper {
    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL < rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    // 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArray(Object[] arr) {
        for (Object anArr : arr) {
            System.out.print(anArr);
            System.out.print(' ');
        }
        System.out.println();
    }

    /**
     * 打印前len个
     *
     * @param arr
     * @param len
     */
    public static void printArray(Object[] arr, int len) {
        if (len > arr.length) {
            len = arr.length;
        }
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    /**
     * 交换数组中两个角标的值
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 校验数组是否排序正确
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
     *
     * @param sortClassName
     * @param arr
     */
    public static void testSort(String sortClassName, Comparable[] arr) {
        try {
            Class<?> sortClass = Class.forName(sortClassName);
            Method method = sortClass.getMethod("sort", Comparable[].class);
            long beforeTime = System.currentTimeMillis();
            method.invoke(null, (Object) arr);
            long afterTime = System.currentTimeMillis();
            //校验
            assert isSorted(arr);
            System.out.println(sortClass.getSimpleName() + " : " + (afterTime - beforeTime) + "ms");
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
