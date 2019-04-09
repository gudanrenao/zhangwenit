package com.zhangwen.learn.zhangwenit.concurrent.tool.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description public CyclicBarrier(int parties, Runnable barrierAction)
 * barrierAction用于在线程到达屏障时，优先执行barrierAction
 * @Author ZWen
 * @Date 2019/4/8 7:22 PM
 * @Version 1.0
 **/
public class CyclicBarrierWithActionTest {


    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new BarrierAction());

    /**
     * 执行顺序为：
     * 1.首先执行每个线程在await()前的代码
     * 2.然后等待所有线程到达屏障后，执行new BarrierAction()一次
     * 3.执行每个线程在await()后的代码
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Test(), "thread-1");
        Thread thread2 = new Thread(new Test(), "thread-2");
        Thread thread3 = new Thread(new Test(), "thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("success");

        //下面为执行结果
//        success
//        Thread[thread-3,5,main]---1
//        Thread[thread-1,5,main]---1
//        Thread[thread-2,5,main]---1
//        Thread[thread-1,5,main]---2
//        Thread[thread-3,5,main]---after wait()
//        Thread[thread-2,5,main]---after wait()
//        Thread[thread-1,5,main]---after wait()
    }

    static class Test implements Runnable {


        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "---1");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + "---after wait()");
        }
    }

    static class BarrierAction implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "---2");

        }
    }
}