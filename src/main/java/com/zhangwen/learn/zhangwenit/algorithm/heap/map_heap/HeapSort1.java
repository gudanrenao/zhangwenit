package com.zhangwen.learn.zhangwenit.algorithm.heap.map_heap;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

import java.util.Arrays;

/**
 * 堆排序1
 * 将数组逐个插入到堆中，再逐个将最大的取出
 *
 * @author zhangwen at 2018-06-24 1:50
 **/
public class HeapSort1 {

    public static void sort(Comparable[] arr) {
        int h = arr.length;
        MaxHeap heap = new MaxHeap<>(h + 1);
        for (int i = 0; i < h; i++) {
            heap.insert(arr[i]);
        }
        for (int i = h - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static void main(String[] args) {
        System.out.println("普通数组排序结果：");
        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, 1000000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "heap.map_heap.HeapSort1", arr4);
        SortTestHelper.printArray(arr4, 10000);

        System.out.println("近乎有序数组排序结果：");
        arr1 = SortTestHelper.generateNearlyOrderedArray(n, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "heap.map_heap.HeapSort1", arr4);
        SortTestHelper.printArray(arr4, 10000);

        System.out.println("大量重复数组排序结果：");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 100);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.merge_sort.MergeSort", arr1);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_advance.quick_sort.QuickSort3Ways", arr3);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "heap.map_heap.HeapSort1", arr4);
        SortTestHelper.printArray(arr4, 10000);
    }
}
