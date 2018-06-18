package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.insertion_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

import java.util.Arrays;

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
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
//                SortTestHelper.swap(arr, j - 1, j);
//            }

            //上面的写法有优化空间，因为频繁的swap有很大的消耗，可以通过临时变量优化
            Comparable n = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(n) > 0; j--) {
                //将前面的元素后移，并将当前元素的新的索引记录
                arr[j] = arr[j - 1];
            }
            arr[j] = n;

        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int n = 20000;
//        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 10000);
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, 1000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.selection_sort_generate_test_cases.SelectionSort", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.insertion_sort.InsertionSort", arr);
    }
}
