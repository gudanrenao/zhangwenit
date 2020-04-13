package com.zhangwen.learn.zhangwenit.concurrent.thread.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Description 模拟数据库连接池
 * @Author ZWen
 * @Date 2020/4/13 1:57 PM
 * @Version 1.0
 **/
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int size) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 将连接放回到连接池
     *
     * @param connection
     * @return
     */
    public void releaseConnection(Connection connection) {
        if (pool != null) {
            synchronized (pool) {
                //连接释放后，需要进行通知，这样的话其他消费者就可以感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取一个连接
     *
     * @param mills 超时毫秒值
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                if (pool.isEmpty()) {
                    return null;
                }
                return pool.removeFirst();
            }
        }
    }
}