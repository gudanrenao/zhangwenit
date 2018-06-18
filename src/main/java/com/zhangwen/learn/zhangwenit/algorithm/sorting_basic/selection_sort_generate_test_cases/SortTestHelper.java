package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort_generate_test_cases;

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

    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
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
