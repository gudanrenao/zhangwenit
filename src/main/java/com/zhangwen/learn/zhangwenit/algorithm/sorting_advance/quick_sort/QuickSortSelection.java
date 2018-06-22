package com.zhangwen.learn.zhangwenit.algorithm.sorting_advance.quick_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

/**
 * 找出数组中第N小的元素
 *
 * @author zhangwen at 2018-06-22 19:38
 **/
public class QuickSortSelection {

    /**
     * 找出数组中第N小的元素
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int n = 157001;
        if (n > arr.length) {
            System.out.println("n不得大于数据长度");
            return;
        }
        quickSort(arr, 0, arr.length - 1, n);
    }

    private static void quickSort(Comparable[] arr, int l, int r, int n) {
        if (l == r) {
            System.out.println("第" + n + "个大的元素的索引为: " + l + "，值为：" + arr[l]);
            return;
        }
        int p = pattern(arr, l, r);
        if (p == n) {
            System.out.println("第" + n + "个大的元素的索引为: " + p + "，值为：" + arr[p]);
            return;
        }
        if (p > n) {
            quickSort(arr, l, p - 1, n);
        } else {
            quickSort(arr, p + 1, r, n);
        }
    }

    private static int pattern(Comparable[] arr, int l, int r) {
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable e = arr[l];
        // arr[l+1...j] arr[j+1...r]
        int j = l;
        for (int k = l + 1; k <= r; k++) {
            if (e.compareTo(arr[k]) > 0) {
                SortTestHelper.swap(arr, k, ++j);
            }
        }
        SortTestHelper.swap(arr, l, j);
        return j;

    }

    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 0, 10000);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSortSelection", arr);
    }
}
