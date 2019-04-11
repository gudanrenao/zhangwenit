package com.zhangwen.learn.zhangwenit.concurrent.executor.ScheduledThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zhangwen.learn.zhangwenit.util.PrintUtils;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/4/11 9:54 AM
 * @Version 1.0
 **/
public class FirstDemo {


    public static void main(String[] args) {
//        scheduleTest();
        executeTest();
    }

    /**
     * 只执行一次原因
     * <pre>
     *
     *     {@code
     *              public void run() {
     *             if (!canRunInCurrentRunState(this))
     *                 cancel(false);
     *             else if (!isPeriodic()) //由于这里间隔时间设为0，但是该判断为true,所以不会重新将任务添加到延时队列中
     *                 super.run();
     *             else if (super.runAndReset()) {
     *                 setNextRunTime();
     *                 reExecutePeriodic(outerTask);
     *             }
     *         }
     *     }
     * </pre>
     */
    private static void executeTest() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5,
                new ThreadFactoryBuilder().setNameFormat("my-scheduled-task-%s").build());
        PrintUtils.printWithTime("running...");
        //只马上执行一次
        scheduledThreadPoolExecutor.execute(() -> {
            PrintUtils.printWithTime("success executed");
        });
    }

    /**
     * <pre>
     * {@code
     *         //scheduleAtFixedRate会把period加一个负号，设置为间隔时间的相反数
     *         private void setNextRunTime() {
     *             long p = period;
     *             if (p > 0)
     *                  //下次执行时间直接等于上次执行开始时间+间隔时间
     *                 time += p;
     *             else
     *                 //下次执行时间等于当前时间(本次已执行完毕)+间隔时间
     *                 time = triggerTime(-p);
     *         }
     * }
     * </pre>
     * <p>
     * 两者区别:
     * 1.scheduleWithFixedDelay固定间隔，如果这次执行耗时1秒，那么下次执行时间为time-1后，
     * 也就是说下次时间包括本次执行耗时，即当前任务开始和下一个任务开始的时间间隔为time
     * 2.scheduleAtFixedRate，如果这次耗时1秒，那么下次再这次执行完成后的time时间后执行，
     * 也就是说这次时间不包括本次执行时间，即当前任务结束和下一个任务开始的时间间隔为time
     */
    private static void scheduleTest() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5,
                new ThreadFactoryBuilder().setNameFormat("my-scheduled-task-%s").build());
        PrintUtils.printWithTime("running...");

        //延时2秒后执行一次，只执行一次原因同execute
        scheduledThreadPoolExecutor.schedule(() -> {
            PrintUtils.printWithTime("after 2 second running");
        }, 2, TimeUnit.SECONDS);

        //下面两个，都是延时2秒后执行一次，之后每隔3秒执行一次
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            PrintUtils.printWithTime("after 2 second running,then fixed delay run with 3 second");
        }, 2, 3, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            PrintUtils.printWithTime("after 2 second running,then fixed rate run with 3 second");
        }, 2, 3, TimeUnit.SECONDS);
    }
}