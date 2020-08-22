package com.zhangwen.learn.zhangwenit.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 自定义有界队列
 * @Author ZWen
 * @Date 2019/4/7 5:11 PM
 * @Version 1.0
 **/
public class BoundedQueue<T> {

    private Object[] items;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private int addIndex;
    private int delIndex;
    private int count;

    public BoundedQueue(int size) {
        this.items = new Object[size];
    }

    /**
     * 添加一个元素，如果数组满了，那么添加线程进入等待状态，直到有"空位"
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            //使用while而非if，是为了防止过早或意外的通知
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 头部删除一个元素，如果数组为空，那么当前线程进入等待队列，直到有新元素添加进来
     *
     * @return
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object t = items[delIndex];
            if (++delIndex == items.length) {
                delIndex = 0;
            }
            count--;
            notFull.signal();
            return (T) t;
        } finally {
            lock.unlock();
        }
    }
}