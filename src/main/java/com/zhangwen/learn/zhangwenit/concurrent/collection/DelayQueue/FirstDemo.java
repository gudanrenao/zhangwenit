package com.zhangwen.learn.zhangwenit.concurrent.collection.DelayQueue;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/10 10:19 AM
 * @Version 1.0
 **/
public class FirstDemo {

    /**
     * todo 1,源码中leader是干啥的：初步理解是减少signal()的使用
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delay> delayQueue = new DelayQueue<>();
        long time1 = System.currentTimeMillis() + 3 * 1000;
        long time2 = System.currentTimeMillis() + 5 * 1000;
        delayQueue.add(new Delay(1005, time2));
        delayQueue.add(new Delay(1003, time1));

        System.out.println("begin --- " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(delayQueue.take() + " ---- " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(delayQueue.take() + " ---- " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(delayQueue.poll(3, TimeUnit.SECONDS) + " ---- " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    static class Delay implements Delayed {

        private final int value;
        /**
         * 过期时间，单位/毫秒
         */
        private final long expiredTime;

        public Delay(int value, long expiredTime) {
            this.value = value;
            this.expiredTime = expiredTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expiredTime - System.currentTimeMillis(), MILLISECONDS);
        }

        /**
         * 按到期时间从小到大排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            Delay that = (Delay) o;
            if (this.expiredTime > that.expiredTime) {
                return 1;
            } else if (this.expiredTime < that.expiredTime) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Delay{" +
                    "value=" + value +
                    ", expiredTime=" + expiredTime +
                    '}';
        }
    }
}