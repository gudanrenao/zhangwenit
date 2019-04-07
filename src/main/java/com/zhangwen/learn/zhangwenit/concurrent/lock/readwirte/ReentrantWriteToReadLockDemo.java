package com.zhangwen.learn.zhangwenit.concurrent.lock.readwirte;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 锁降级demo
 * @Author ZWen
 * @Date 2019/4/7 4:41 PM
 * @Version 1.0
 **/
public class ReentrantWriteToReadLockDemo {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void demo(boolean update) {
        lock.readLock().lock();
        if (!update) {
            //必须先释放读锁
            lock.readLock().unlock();

            //锁降级从写锁获取开始
            lock.writeLock().lock();
            try {
                if (!update) {
                    update = true;
                }
                lock.readLock().lock();
            } finally {
                lock.writeLock().unlock();
            }
            //锁降级完成，写锁降级为读锁
        }

        try {
            //后续使用数据流程
        } finally {
            lock.readLock().unlock();
        }
    }
}