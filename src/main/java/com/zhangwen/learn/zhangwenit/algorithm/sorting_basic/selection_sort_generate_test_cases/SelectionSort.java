package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort_generate_test_cases;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

import java.util.Arrays;

/**
 * 测试数据生成测试
 *
 * @author zhangwen at 2018-06-17 0:29
 **/
public class SelectionSort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                //使用compareTo方法比较两个Comparable对象的大小
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        // 测试排序算法辅助函数
        int n = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 10000);
        Integer[] arr2 = Arrays.copyOf(arr, n);
//        SelectionSort.sort(arr);
//        SortTestHelper.printArray(arr);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit." +
                "algorithm.sorting_basic.selection_sort_generate_test_cases.SelectionSort", arr);
    }
}
