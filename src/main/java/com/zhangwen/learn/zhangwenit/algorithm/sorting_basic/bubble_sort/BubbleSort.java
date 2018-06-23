package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.bubble_sort;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

/**
 * 冒泡排序
 *
 * @author zhangwen at 2018-06-19 10:17
 **/
public class BubbleSort {

    public static void sort(Comparable[] arr) {
        //比较相邻元素，将大的后移
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    SortTestHelper.swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * sort2和sort3本质一样
     * 都是在每次第二层循环后判断该次循环是否有过交换发生
     * 如果没有，则该数组已经是排好序的，无需进行后续的第一层循环
     *
     * @param arr
     */
    public static void sort2(Comparable[] arr) {
        //比较相邻元素，将大的后移
        for (int i = arr.length - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    SortTestHelper.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void sort3(Comparable[] arr) {

        int n = arr.length;
        boolean swapped = false;
        //int newn; // 理论上,可以使用newn进行优化,但实际优化效果较差

        do {
            swapped = false;
            //newn = 0;
            for (int i = 1; i < n; i++)
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    SortTestHelper.swap(arr, i - 1, i);
                    swapped = true;

                    // 可以记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    // 实际优化效果较差,因为引入了newn这个新的变量
                    //newn = n;
                }

            //n = newn;

            // 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
            // 所以下一次排序, 最后的元素可以不再考虑
            // 理论上, newn的优化是这个优化的复杂版本,应该更有效
            // 实测, 使用这种简单优化, 时间性能更好
            n--;
        } while (swapped);
    }

    public static void main(String[] args) {
//        Integer[] arr = SortTestHelper.generateRandomArray(10000, 0, 10000);
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(10000, 0);
        SortTestHelper.testSort("com.zhangwen.learn.zhangwenit.algorithm." +
                "sorting_basic.bubble_sort.BubbleSort", arr);
        SortTestHelper.printArray(arr);
    }
}
