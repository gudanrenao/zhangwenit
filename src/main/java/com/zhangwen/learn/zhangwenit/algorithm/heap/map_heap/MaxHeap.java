package com.zhangwen.learn.zhangwenit.algorithm.heap.map_heap;

import com.zhangwen.learn.zhangwenit.algorithm.util.SortTestHelper;

/**
 * 最大堆基本实现和操作（元素从数组下标1开始）
 *
 * @author zhangwen at 2018-06-23 17:37
 **/
public class MaxHeap<Item extends Comparable> {

    /**
     * 元素存储结构
     */
    protected Item[] data;
    /**
     * 当前存入元素个数
     */
    protected int count;
    /**
     * 最大可存储容量
     */
    protected int capacity;

    public MaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 向堆中插入一个元素
     *
     * @param item
     */
    public void insert(Item item) {
        //保证不会超出最大容量
        assert (capacity >= count + 1);
        count++;
        data[count] = item;
        //进行shiftUp操作，保持最大堆特性
        shiftUp(count);
    }

    /**
     * 最大堆核心辅助函数
     *
     * @param k
     */
    private void shiftUp(int k) {
        //如果k不是第一个元素，且k所在元素大于父节点，交换位置，并继续下一轮比较
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            SortTestHelper.swap(data, k, k / 2);
            k /= 2;
        }
    }

    /**
     * 取出最大的元素
     *
     * @return
     */
    public Item extractMax() {
        Item i = data[1];
        SortTestHelper.swap(data, 1, count);
        count--;
        //将交换后的第一个位置的元素，向下交换已维持最大堆特性
        shiftDown(1);
        return i;
    }

    /**
     * 向下交换
     *
     * @param k
     */
    private void shiftDown(int k) {
        //将k与子节点中的最大的那个比较，如果比子节点中最大的小，则交换两者
        //边界判断，左子节点<=count
        while (2 * k <= count) {
            //j为子节点中最大的那个
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j += 1;
            }
            //如果比子节点都大，停止循环
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            SortTestHelper.swap(data, k, j);
            k = j;
        }
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 返回堆中元素数量
     *
     * @return
     */
    public int size() {
        return count;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        // 堆中元素个数
        int N = 100;
        // 堆中元素取值范围[0, M)
        int M = 100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert((int) (Math.random() * M));
        }


        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for (int i = 1; i < N; i++) {
            assert arr[i - 1] >= arr[i];
        }
    }
}

