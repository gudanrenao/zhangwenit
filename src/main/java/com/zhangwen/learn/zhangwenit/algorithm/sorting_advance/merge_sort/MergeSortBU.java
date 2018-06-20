package com.zhangwen.learn.zhangwenit.algorithm.sorting_advance.merge_sort;

import com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.util.SortTestHelper;

import java.util.Arrays;

/**
 * 归并排序(自底向上)
 *
 * @author zhangwen at 2018-06-20 10:23
 **/
public class MergeSortBU {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        //无优化版本
//        for (int sz = 1; sz < n; sz += sz) {
//            for (int i = 0; i + sz < n; i += sz + sz) {
//                MergeSort.merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
//            }
//        }

        //优化版本1
        for (int sz = 1; sz < n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    MergeSort.merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 500000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100000);
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(1000000, 0);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSortBU", arr);
        SortTestHelper.printArray(arr, 10000);
//        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
//                "sorting_basic.insertion_sort.InsertionSort", arr2);

    }


//    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
//    private static void merge(Comparable[] arr, int l, int mid, int r) {
//
//        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
//
//        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
//        int i = l, j = mid + 1;
//        for (int k = l; k <= r; k++) {
//
//            if (i > mid) {  // 如果左半部分元素已经全部处理完毕
//                arr[k] = aux[j - l];
//                j++;
//            } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
//                arr[k] = aux[i - l];
//                i++;
//            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {  // 左半部分所指元素 < 右半部分所指元素
//                arr[k] = aux[i - l];
//                i++;
//            } else {  // 左半部分所指元素 >= 右半部分所指元素
//                arr[k] = aux[j - l];
//                j++;
//            }
//        }
//    }
//
//    public static void sort(Comparable[] arr){
//
//        int n = arr.length;
//
//        // Merge Sort Bottom Up 无优化版本
////        for (int sz = 1; sz < n; sz *= 2)
////            for (int i = 0; i < n - sz; i += sz+sz)
////                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
////                merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1));
//
//        // Merge Sort Bottom Up 优化
//        // 对于小数组, 使用插入排序优化,有问题！！！！！
////        for( int i = 0 ; i < n ; i += 16 )
////            InsertionSort.insertSort(arr, i, Math.min(i+15, n-1) );
//
//        for( int sz = 1; sz < n ; sz += sz )
//            for( int i = 0 ; i < n - sz ; i += sz+sz )
//                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
//                if( arr[i+sz-1].compareTo(arr[i+sz]) > 0 )
//                    merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1) );
//
//    }
}
