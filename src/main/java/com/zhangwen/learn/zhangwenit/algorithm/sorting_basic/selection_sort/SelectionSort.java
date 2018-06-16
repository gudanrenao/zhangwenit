package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort;

/**
 * 选择排序
 *
 * @author zhangwen at 2018-06-16 23:09
 **/
public class SelectionSort {

    private SelectionSort() {
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int t = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = t;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
}
