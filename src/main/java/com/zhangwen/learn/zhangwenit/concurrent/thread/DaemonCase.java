package com.zhangwen.learn.zhangwenit.concurrent.thread;

/**
 * @Description 虚拟机退出时，Daemon线程的finally语句块未必会执行，不可以在Daemon线程中的finally语句块释放资源
 * @Author ZWen
 * @Date 2020/4/12 2:53 PM
 * @Version 1.0
 **/
public class DaemonCase {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Daemon());
            //setDaemon为true时，finally不会执行，不设置时会执行
            thread.setDaemon(true);
            thread.start();
        }

    }

    private static class Daemon implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("daemon thread running");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("daemon thread finally exec");
            }
        }
    }
}
