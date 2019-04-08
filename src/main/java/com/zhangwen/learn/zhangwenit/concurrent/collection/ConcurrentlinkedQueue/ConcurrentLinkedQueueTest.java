package com.zhangwen.learn.zhangwenit.concurrent.collection.ConcurrentlinkedQueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/8 12:05 PM
 * @Version 1.0
 **/
public class ConcurrentLinkedQueueTest {

    /**
     * 入队时做两件事情
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        /**
         * public boolean add(E e) {
         *         return offer(e);
         *     }
         *     add = offer
         */
        concurrentLinkedQueue.add("1");

        concurrentLinkedQueue.offer("2");
        concurrentLinkedQueue.offer("3");
        concurrentLinkedQueue.offer("4");
        concurrentLinkedQueue.offer("5");
        concurrentLinkedQueue.offer("6");
        concurrentLinkedQueue.offer("7");
        concurrentLinkedQueue.remove("5");
    }
}