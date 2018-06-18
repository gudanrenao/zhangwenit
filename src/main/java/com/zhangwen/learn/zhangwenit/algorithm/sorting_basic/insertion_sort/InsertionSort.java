package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.insertion_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

/**
 * 插入排序
 *
 * @author zhangwen at 2018-06-18 18:11
 **/
public class InsertionSort {

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //写法一

//            for (int j = i - 1; j >= 0; j--) {
//                if (arr[j + 1].compareTo(arr[j]) < 0) {
//                    SortTestHelper.swap(arr, j + 1, j);
//                } else {
//                    break;
//                }
//            }

            //写法二
            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                SortTestHelper.swap(arr, j - 1, j);
            }
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int n = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 1000000);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.insertion_sort.InsertionSort", arr);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.selection_sort_generate_test_cases.SelectionSort", arr);
    }
}
