package com.zhangwen.learn.zhangwenit.java8.newFeature;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description 新特性测试
 * @Author ZWen
 * @Date 2019/1/2 2:12 PM
 * @Version 1.0
 **/
public class NewFeatureTest {

    public static void main(String[] args) {
        Integer c = 12;
        MathOperation add = (a, b) -> a + b + c;
        int a = 1;
        int b = 2;
        System.out.println("a + b = " + add.operation(a, b));
        String hello = "hello! ";
        Greeting greeting = msg -> System.out.println(hello + msg);
        greeting.say("zw");

        //函数式接口
        List<Integer> evalList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        eval(evalList, e -> e % 2 == 0);
        //stream
        long sum = evalList.stream().mapToLong(Long::valueOf).sum();
        System.out.println(sum);
        System.out.println(evalList.stream().sorted((n1, n2) -> n2 - n1).limit(5).mapToLong(Long::valueOf).sum());
        System.out.println(evalList.stream().map(Objects::toString).collect(Collectors.joining("-")));
        System.out.println(evalList.stream().map(Objects::toString).collect(Collectors.joining(",", "[", "]")));
        //stream 统计
        IntSummaryStatistics statistics = evalList.stream().mapToInt(e -> e).summaryStatistics();
        System.out.println("max=" + statistics.getMax() + ",min=" + statistics.getMin() + ",avg=" + statistics.getAverage());

        //默认方法
        System.out.println();
        System.out.println(new DefaultMethodImpl().test());

        //默认静态方法
        MathOperation.staticTest();

        //option
        Optional<Integer> optional = Optional.of(100);
        optional.ifPresent(e -> System.out.println("ifPresent: " + e));
        System.out.println("optional map: " + optional.map(e -> e + 200).get());

        //日期时间
        LocalDateTime currDateTime = LocalDateTime.now();
        System.out.println("当前时间：" + currDateTime);
        LocalDate currDate = currDateTime.toLocalDate();
        System.out.println("当前日期：" + currDate);
        LocalTime currTime = currDateTime.toLocalTime();
        System.out.println("当前时分秒：" + currTime);
        LocalDateTime dateTime = currDateTime.withMonth(2).withDayOfMonth(15).withNano(0).withSecond(1);
        System.out.println("withMethod: " + dateTime.toString());
        System.out.println("getMonth(单词): " + currDateTime.getMonth() + " , getMonthValue: " + currDateTime.getMonthValue());
        System.out.println("getDayOfYear: " + dateTime.getDayOfYear() + ", getDayOfMonth: " + dateTime.getDayOfMonth()
                + " , getDayOfWeek(单词): " + dateTime.getDayOfWeek());
        System.out.println(LocalDateTime.parse("2019-01-12T02:02:02"));

        //时区日期时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区日期时间: " + zonedDateTime);
        System.out.println("getAvailableZoneIds: " + ZoneId.getAvailableZoneIds());

        //和Date的转换
        //Date转换为LocalDateTime
        Date date = new Date();
        ZonedDateTime atZone = date.toInstant().atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime1 = atZone.toLocalDateTime();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
        System.out.println("date to localDateTime: " + localDateTime1 + " , " + localDateTime2);
        //LocalDateTime转换为Date
        Date from = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("localDateTime to date: " + from);

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(e -> {
            if (predicate.test(e)) {
                System.out.print(e + " , ");
            }
        });
    }


    public static class DefaultMethodImpl implements MathOperation, Greeting {

        @Override
        public int operation(int a, int b) {
            return 0;
        }

        @Override
        public void say(String msg) {

        }

        @Override
        public String test() {
            //默认方法
            return Greeting.super.test();
        }
    }

    interface MathOperation {
        int operation(int a, int b);

        default String test() {
            return "MathOperation";
        }

        static void staticTest() {
            System.out.println("i am static");
        }
    }

    interface Greeting {
        void say(String msg);

        default String test() {
            return "Greeting";
        }
    }
}