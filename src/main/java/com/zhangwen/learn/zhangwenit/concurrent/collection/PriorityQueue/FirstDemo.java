package com.zhangwen.learn.zhangwenit.concurrent.collection.PriorityQueue;

import java.util.PriorityQueue;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/10 10:22 AM
 * @Version 1.0
 **/
public class FirstDemo {

    /**
     * 使用堆排序实现
     *
     * @param args
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o1 > o2 ? -1 : (o1.equals(o2) ? 0 : 1));

        //add -> offer
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(30);

        for (int i = 0; i < priorityQueue.size(); i++) {
            //peek方法只会拿到第一个元素，不会弹出，所以不管循环多少次，都是同一个元素
            System.out.println(priorityQueue.peek());
        }

        System.out.println("====================");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }


    }
}