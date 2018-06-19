package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.shell_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author zhangwen at 2018-06-19 11:40
 **/
public class ShellSort {

    public static void sort(Comparable[] arr) {
        //先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组。
        // 所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；
        // 然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量  =1(  <  …<d2<d1)，
        // 即所有记录放在同一组中进行直接插入排序为止。
        //该方法实质上是一种分组插入方法
        int len = arr.length;
        int n = 1;
        while (n < len / 3) {
            n = 3 * n + 1;
        }
        while (n >= 1) {
            for (int j = n; j < len; j++) {
                Comparable e = arr[j];
                int index = j;
                for (; index >= n && e.compareTo(arr[index - n]) < 0; index = index - n) {
                    arr[index] = arr[index - n];
                }
                arr[index] = e;
            }
            n /= 3;
        }
    }

    public static void sort2(Comparable[] arr) {
        int n = arr.length;
        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h)
                    arr[j] = arr[j - h];
                arr[j] = e;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 10000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.insertion_sort.InsertionSort", arr2);
        SortTestHelper.printArray(arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.shell_sort.ShellSort", arr);
        SortTestHelper.printArray(arr);
    }
}
