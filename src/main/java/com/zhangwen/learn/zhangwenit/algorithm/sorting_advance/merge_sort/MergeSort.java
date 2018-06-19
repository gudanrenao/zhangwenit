package com.zhangwen.learn.zhangwenit.algorithm.sorting_advance.merge_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author zhangwen at 2018-06-19 20:32
 **/
public class MergeSort {


    public static void sort(Comparable[] arr) {
        //将数组一分为二，递归对每个部分进行排序
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归使用归并排序，对arr[l,r]进行排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(Comparable[] arr, int l, int r) {
        //递归终止条件
        if (l >= r) {
            return;
        }
        //否则，将数组一分为二，分别进行排序
        int middle = (l + r) / 2;
        mergeSort(arr, l, middle);
        mergeSort(arr, middle + 1, r);
        //归并排序
        merge(arr, l, middle, r);
    }

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        //首先将待排序数组copy
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        //记录前后两个部分的起始下标
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            //为防止某个部分的元素全部赋值后，继续比较造成的角标越界等错误，需进行判断某个部分是否到达临界角标
            if (i > mid) {
                // 如果左半部分元素已经全部处理完毕,将另一部分的元素全部赋值
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {
                // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 500000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 10000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.shell_sort.ShellSort", arr2);

    }

}
