package com.zhangwen.learn.zhangwenit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //输出系统换行符
        String lineSeparator = System.lineSeparator();
        System.out.println(lineSeparator);

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
//
//
//        CopyOnWriteArrayList<TempOpenId> list = new CopyOnWriteArrayList<>();
//        List<String> baseDirs = new ArrayList<>();
//        baseDirs.add("/Users/zhangwen/Desktop/prod-1");
//        baseDirs.add("/Users/zhangwen/Desktop/prod-2");
//        String pattern = "yyyy-MM-dd HH:mm:ss";
//
//        for (String baseDir : baseDirs) {
//            File baseFile = new File(baseDir);
//            File[] files = baseFile.listFiles();
//            for (File file : files) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String str = null;
//                while ((str = reader.readLine()) != null) {
//                    if (str.contains("TemplateMsgSendService|消息发送成功")) {
//                        TempOpenId tempOpenId = new TempOpenId();
//                        int begin = str.indexOf("[");
//                        int end = str.indexOf("]");
//                        tempOpenId.setOpenId(str.substring(begin + 1, end));
//                        int time = str.indexOf(".");
//                        String date = str.substring(0, time);
//                        Date sendTime = DateUtils.parseDate(date, pattern);
//                        tempOpenId.setSendTime(sendTime);
//                        int typeBegin = str.lastIndexOf("[");
//                        int typeEnd = str.lastIndexOf("]");
//                        String temId = str.substring(typeBegin + 1, typeEnd);
//
//                        switch (temId) {
//                            case "ciDCCSv5A6HsDVVshz5A9KKpEjRv6W6LaKF3i6HJfz0":
//                                tempOpenId.setType("预约成功");
//                                break;
//                            case "KGeKJT1aQxKQmjVA2JH8m4SE3joGGsaDOmFSC7emsOo":
//                                tempOpenId.setType("挂号成功");
//                                break;
//                            case "lQ5KEbiir73_KeVd_I_ofE3D8wQN79tmVsU8PoUMuAA":
//                                tempOpenId.setType("缴费提醒");
//                                break;
//                            case "iyeN5oUDPLMDImnivaqaBgeU2LK8CwAUm1g4bEOO5ww":
//                                tempOpenId.setType("报告结果");
//                                break;
//                            case "xtaASe6efTG1B6wvb8gztBN-zUGxlWY11uU2JhW86yQ":
//                                tempOpenId.setType("就诊排队");
//                                break;
//                            case "-be_TvByXndVuAI8lPAvQcknVY2DARWAxY8bawte7hY":
//                                tempOpenId.setType("住院押金");
//                                break;
//                            default:
//                                tempOpenId.setType("未知");
//                        }
//                        list.add(tempOpenId);
//                    }
//                }
//                reader.close();
//            }
//        }
//
//        System.out.println(list.size());


//        System.out.println(null + "121212");
        System.out.println(11 & 3);


        AtomicInteger atomicInteger = new AtomicInteger(100000);
        List<Integer> list1 = Stream.generate(atomicInteger::getAndDecrement).limit(100000).collect(Collectors.toList());
        atomicInteger.set(1);
        List<Integer> list2 = Stream.generate(atomicInteger::getAndIncrement).limit(1000).collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        long begin = System.currentTimeMillis();
        for (Integer i1 : list1) {
            for (Integer i2 : list2) {
                if (i2.equals(i1)) {
                    result.add(i1);
                    break;
                }
            }
        }
        System.out.println("use time is " + (System.currentTimeMillis() - begin));
        System.out.println("result size is " + result.size());
        System.out.println("-----------------------------------");
        List<Integer> result2 = new ArrayList<>();
        long begin2 = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i2 : list2) {
            map.put(i2, i2);
        }
        for (Integer i1 : list1) {
            if (map.containsKey(i1)) {
                result2.add(i1);
            }
        }
        System.out.println("use time is " + (System.currentTimeMillis() - begin2));
        System.out.println("result2 size is " + result2.size());
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