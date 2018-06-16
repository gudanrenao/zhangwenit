package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort_generate_test_cases;

/**
 * 测试用随机数组工具类
 *
 * @author zhangwen at 2018-06-17 0:24
 **/
public class SortTestHelper {
    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL < rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
