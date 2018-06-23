package com.zhangwen.learn.zhangwenit.algorithm.sorting_advance.quick_sort;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

import java.util.Arrays;

/**
 * 三路快速排序(对有大量重复key的数据效率高)
 *
 * @author zhangwen at 2018-06-21 18:00
 **/
public class QuickSort3Ways {

    public static void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归快速排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void quickSort(Comparable[] arr, int l, int r) {
        //递归终止条件，当l >= r
        if (l >= r) {
            return;
        }
        //partition
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        Comparable e = arr[l];
        //arr[l+1...lt] < e , arr[lt+1...i) = e , arr[gt,r] > e
        //初始化关键索引位置
        int lt = l, gt = r + 1, i = l + 1;
        while (i < gt) {
            if (arr[i].compareTo(e) < 0) {
                //交换
                SortTestHelper.swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i].compareTo(e) > 0) {
                SortTestHelper.swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        SortTestHelper.swap(arr, l, lt);

        //递归
        quickSort(arr, l, lt - 1);
        quickSort(arr, gt, r);
    }

    public static void main(String[] args) {
        System.out.println("普通数组排序结果：");
        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, 1000000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);

        System.out.println("近乎有序数组排序结果：");
        arr1 = SortTestHelper.generateNearlyOrderedArray(n, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);
        SortTestHelper.printArray(arr2,10000);

        System.out.println("大量重复数组排序结果：");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 100);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);
    }
}
