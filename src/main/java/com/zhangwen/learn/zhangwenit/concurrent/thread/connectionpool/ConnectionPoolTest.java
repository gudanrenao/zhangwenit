package com.zhangwen.learn.zhangwenit.concurrent.thread.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/4/13 2:32 PM
 * @Version 1.0
 **/
public class ConnectionPoolTest {

    private static ConnectionPool pool = new ConnectionPool(10);

    private static CountDownLatch begin = new CountDownLatch(1);
    private static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //thread size 可以修改，查看效果
        int threadSize = 20;
        end = new CountDownLatch(threadSize);
        //每个thread获取并释放20次连接
        int count = 20;
        AtomicInteger gotCount = new AtomicInteger(0);
        AtomicInteger notGotCount = new AtomicInteger(0);

        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(new ThreadRunner(count, gotCount, notGotCount), "connectionRunnerThread");
            thread.start();
        }
        begin.countDown();
        end.await();
        System.out.println("exec count is " + threadSize * count);
        System.out.println("got size is " + gotCount.get());
        System.out.println("notGot size is " + notGotCount.get());
    }

    private static class ThreadRunner implements Runnable {

        private int count;
        private AtomicInteger gotCount;
        private AtomicInteger notGotCount;

        public ThreadRunner(int count, AtomicInteger gotCount, AtomicInteger notGotCount) {
            this.count = count;
            this.gotCount = gotCount;
            this.notGotCount = notGotCount;
        }

        @Override
        public void run() {
            try {
                begin.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection == null) {
                        notGotCount.incrementAndGet();
                    } else {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            gotCount.incrementAndGet();
                        }
                    }

                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}