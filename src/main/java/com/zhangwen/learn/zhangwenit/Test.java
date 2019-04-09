package com.zhangwen.learn.zhangwenit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.text.ParseException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 临时
 * @Author ZWen
 * @Date 2019/1/9 5:11 PM
 * @Version 1.0
 **/
public class Test {

    private int count;

    private static final VarHandle COUNT;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            COUNT = lookup.findVarHandle(Test.class, "count", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws ParseException, InterruptedException, IOException {

//        TestRun testRun = new TestRun();
//        testRun.start();
//
//        long useTimes = 0;
//        System.out.println(useTimes);
//        Thread.sleep(60 * 1000);

//        String s = "！23a";
//
//        System.out.println(s.length());
//        System.out.println(s.getBytes().length);
//
//        List<Integer> integerList = Arrays.asList(1, 2, 3);
//        integerList.add(5);

//        StreamUtils.copyToByteArray(null);
//            HttpServletRequest request = null;
//        ArrayList<String> list = Collections.list(request.getHeaders("aaa"));

//        System.out.println(StandardCharsets.UTF_8.toString());


        //VarHandle
//        Test test = new Test();
//        COUNT.set(test,200);
//        System.out.println(test);

//        System.out.println( (long)(1.0 + (long)16 / 0.75));

//        System.out.println(32 >> 1);
//
//        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
//
//        concurrentHashMap.put("1","100");
//        concurrentHashMap.put("2","200");
//        concurrentHashMap.put("3","300");
//
//        System.out.println(concurrentHashMap.get("1"));
//        System.out.println(concurrentHashMap.size());

        int COUNT_BITS = Integer.SIZE - 3;
//        int COUNT_MASK = (1 << COUNT_BITS) - 1;
//        System.out.println(8 & COUNT_MASK);

        int a = 2 << COUNT_BITS;



    }




    private static class TestRun implements Runnable {

        private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder()
                        .setNameFormat("DiscoveryClient-InstanceInfoReplicator-%d")
                        .setDaemon(true)
                        .build());


        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            logger.error("run begin ............");
            Future next = scheduler.schedule(this, 10, TimeUnit.SECONDS);
            logger.error("run end ............");

        }

        public void start() {
            logger.error("start begin ............");
            //这个调用后，会触发TestRun的run方法
            scheduler.schedule(this, 5, TimeUnit.SECONDS);
            logger.error("start end ............");
        }
    }
}