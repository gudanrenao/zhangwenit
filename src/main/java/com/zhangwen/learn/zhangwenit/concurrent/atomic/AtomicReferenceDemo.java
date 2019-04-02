package com.zhangwen.learn.zhangwenit.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description AtomicReference cas count demo  todo:有问题
 * @Author ZWen
 * @Date 2019/4/2 7:22 PM
 * @Version 1.0
 **/
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        CountDemo countDemo = new CountDemo();
        AtomicReference<CountDemo> atomicReference = new AtomicReference<>(countDemo);
        long start = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>(100);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    for (; ; ) {
                        CountDemo demo = new CountDemo(atomicReference.get().getCount1() + 1, atomicReference.get().getCount2() + 2);
                        boolean set = atomicReference.compareAndSet(countDemo, demo);
                        if (set) {
                            break;
                        }
                    }
                }
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count1 = " + countDemo.getCount1());
        System.out.println("count2 = " + countDemo.getCount2());
        System.out.println("use time = " + (System.currentTimeMillis() - start));
    }

    static class CountDemo {
        private int count1;
        private int count2;

        public CountDemo() {
        }

        public CountDemo(int count1, int count2) {
            this.count1 = count1;
            this.count2 = count2;
        }

        public int getCount1() {
            return count1;
        }

        public void setCount1(int count1) {
            this.count1 = count1;
        }

        public int getCount2() {
            return count2;
        }

        public void setCount2(int count2) {
            this.count2 = count2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CountDemo)) return false;
            CountDemo countDemo = (CountDemo) o;
            return getCount1() == countDemo.getCount1() &&
                    getCount2() == countDemo.getCount2();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCount1(), getCount2());
        }
    }
}