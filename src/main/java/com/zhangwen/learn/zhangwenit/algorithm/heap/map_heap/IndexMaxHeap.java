package com.zhangwen.learn.zhangwenit.algorithm.heap.map_heap;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

/**
 * 最大索引堆
 *
 * @author zhangwen at 2018-06-28 11:19
 **/
public class IndexMaxHeap<Item extends Comparable> {

    /**
     * 数据
     */
    private Item[] data;
    /**
     * 数据的索引堆
     */
    private int[] indexes;
    /**
     * 当前堆中元素数量
     */
    private int count;
    /**
     * 数据和堆的最大容量
     */
    private int capacity;

    public IndexMaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 向堆中插入元素
     *
     * @param i    元素索引，注意：从0开始
     * @param item
     */
    public void insert(int i, Item item) {
        //检验堆不会角标越界
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;
        //最大堆索引从1开始
        i += 1;

        data[i] = item;
        indexes[count + 1] = i;
        count++;
        //shiftUp
        shiftUp(count);
    }

    /**
     * 取出最大元素的索引
     *
     * @return
     */
    public int extractMaxIndex() {
        assert count > 0;

        int i = indexes[1];
        //交换首尾并进行shiftDown
        SortTestHelper.swap(indexes, 1, count);
        count--;
        shiftDown(1);

        return i - 1;
    }

    /**
     * 获取某个索引的值
     *
     * @param i
     * @return
     */
    public Item getItem(int i) {
        return data[i + 1];
    }

    /**
     * 修改某个索引的值
     *
     * @param i
     * @param newItem
     */
    public void change(int i, Item newItem) {
        i++;
        data[i] = newItem;
        //需要找到indexes[j] = i中的j,对该位置进行shiftUp和shiftDown操作
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j += 1;
            }
            if (data[indexes[j]].compareTo(data[indexes[k]]) < 0) {
                break;
            }
            SortTestHelper.swap(indexes, k, j);
            k = j;
        }
    }

    /**
     * 对索引堆中i位置操作
     *
     * @param i
     */
    private void shiftUp(int i) {
        while (i > 1 && data[indexes[i / 2]].compareTo(data[indexes[i]]) < 0) {
            SortTestHelper.swap(indexes, i / 2, i);
            i /= 2;
        }
    }

    /**
     * 获取堆最大容量
     *
     * @return
     */
    public int size() {
        return capacity;
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

}
