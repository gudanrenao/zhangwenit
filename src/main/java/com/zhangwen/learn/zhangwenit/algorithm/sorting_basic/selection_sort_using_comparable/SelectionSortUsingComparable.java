package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort_using_comparable;

/**
 * 选择排序（使用模板泛型）
 *
 * @author zhangwen at 2018-06-16 23:09
 **/
public class SelectionSortUsingComparable {

    private SelectionSortUsingComparable() {
    }

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int i, int minIndex) {
        Object t = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = t;
    }

    public static void main(String[] args) {
        // 测试Double
        Double[] b = {4.4, 3.3, 2.2, 1.1};
        SelectionSortUsingComparable.sort(b);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
            System.out.print(' ');
        }
        System.out.println();

        // 测试自定义的类 Student
        Student[] d = new Student[4];
        d[0] = new Student("D", 90);
        d[1] = new Student("C", 100);
        d[2] = new Student("B", 95);
        d[3] = new Student("A", 95);
        SelectionSortUsingComparable.sort(d);
        for (Student aD : d) {
            System.out.println(aD);
        }
    }
}
