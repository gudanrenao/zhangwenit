package com.zhangwen.learn.zhangwenit.algorithm.sorting_advance.quick_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author zhangwen at 2018-06-20 12:09
 **/
public class QuickSort {

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
        //获取快速排序后partition操作后的p的索引
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 快速排序操作,使得arr[l...p-1]<arr[p],arr[p+1...r] > arr[p]
     *
     * @param arr
     * @param l
     * @param r
     * @return p
     */
    private static int partition(Comparable[] arr, int l, int r) {
        //已第一个值为比较值
        Comparable e = arr[l];
        //比较点最后所在索引,关键：同时也是比较过程中比 e 小的数组的最后一个元素索引!!!
        //因为在全部比较完之后，会把 e 与 该元素交换位置
        int p = l;
        //根据定义，需使得arr[l + 1...p] < e,arr[p+1...i) < e
        // 注意：后面的数据是前闭后开区间！！！，因为当前i元素是将要参与比较的下一个元素
        for (int i = p + 1; i <= r; i++) {
            //如果该值大于e,则无需任何操作
            //如果小于e,则需要把arr[p+1...i)的e[p+1]和arr[i]进行交换，并且p后移一位！！！
            if (arr[i].compareTo(e) < 0) {
                SortTestHelper.swap(arr, i, p + 1);
                p++;
                //上面两行可以合并改写为下面这条语句
                // SortTestHelper.swap(arr, i, ++p);
            }
        }
        //arr[l]与 arr[p]交换
        SortTestHelper.swap(arr, l, p);
        return p;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0,1000000);
        Integer[] arr2 = Arrays.copyOf(arr,n);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort",arr);
        SortTestHelper.printArray(arr,10000);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort",arr2);
        SortTestHelper.printArray(arr2,10000);
    }
}
