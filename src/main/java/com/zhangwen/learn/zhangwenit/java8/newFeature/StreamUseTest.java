package com.zhangwen.learn.zhangwenit.java8.newFeature;

import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description stream 用法示例
 * @Author ZWen
 * @Date 2019/1/29 10:42 AM
 * @Version 1.0
 **/
public class StreamUseTest {

    public static void main(String[] args) {
//        peek();
//        reduce();
//        skip();
//        limit();
//        sortUseLimitOrSkipOrFilter();
//        wordsDistinctListFromFile();
//        match();
//        generateStream();
//        iterate();
//        partitioningBy();
        groupBy();
    }

    public static void groupBy(){
        List<User> userList = getUserList(10);
        Map<String, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::groupBy));
        System.out.println(collect);
    }

    public static void partitioningBy(){
        List<User> userList = getUserList(10);
        Map<Boolean, List<User>> collect = userList.stream().collect(Collectors.partitioningBy(User::partitioningBy));
        System.out.println(collect);
    }

    public static void iterate() {
        Stream.iterate(1, n -> 2 * n).limit(10).forEach(System.out::println);
    }

    /**
     * 自己生成流，由于它是无限的，在管道中，必须使用limit之类的操作限制stream的大小
     */
    public static void generateStream() {
//        Random random = new Random();
//        Supplier<Integer> generateInt = random::nextInt;
//        Stream.generate(generateInt).limit(10).forEach(System.out::println);
        Stream.generate(new UserSupplier()).limit(10).forEach(System.out::println);

    }

    private static class UserSupplier implements Supplier<User> {

        @Override
        public User get() {
            User user = new User();
            user.setId((long) new Random().nextInt(10000));
            user.setName("zw:" + user.getId());
            return user;
        }
    }

    public static void match() {
        //allMatch,只有全部的元素都满足条件，才返回true,当某个元素不满足条件是，不会继续判断下一个，直接返回false
        List<User> list = getUserList(3);
        list.get(2).setPassword("12");
        list.get(1).setPassword("12");
        boolean allMatch = list.stream().peek(System.out::println).allMatch(e -> StringUtils.isNotEmpty(e.getPassword()));
        System.out.println("allMatch : " + allMatch);
        //anyMatch,只要某个元素满足，直接返回true,后面不会执行
        boolean anyMatch = list.stream().peek(System.out::println).anyMatch(e -> StringUtils.isNotEmpty(e.getPassword()));
        System.out.println("anyMatch : " + anyMatch);
        //noneMatch,任何一个都不满足，返回true,如果某个满足，直接返回false,后面不会执行
        boolean noneMatch = list.stream().peek(System.out::println).noneMatch(e -> StringUtils.isNotEmpty(e.getPassword()));
        System.out.println("noneMatch : " + noneMatch);
    }

    /**
     * 从文件中统计所有的单词去重，并转小写
     */
    public static void wordsDistinctListFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/zhangwen/work/code/zhangwenit/src/main/resources/stream/test"));
            List<String> stringList = reader.lines().flatMap(line -> Stream.of(line.split(" ")))
                    .filter(e -> e.length() > 0)
                    .map(String::toLowerCase)
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(stringList);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortUseLimitOrSkipOrFilter() {
        //需要先使用limit、skip、filter等过滤操作，之后再用sort排序，将会减少比较的次数
        //如果先使用sort，再过滤，sort将会对所以的元素进行排序操作，因为它不知道后续的执行结果
        List<User> list = getUserList(10);
        //对于compare,this(a)代表的是原数组或列表中的后面的元素，Object参数(b)代表的是前面的元素
        //当 a > b，返回1时，也就是说当后面的元素>前面的元素时，两者的位置不需要改变，这个时候就是升序
        //反之，当a > b时，返回-1，也就是说当后面的元素>前面的元素时，两者的位置需要调换，这个时候就是倒序
        List<User> collect = list.stream().limit(5).sorted(User::compareTo).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //另外，对一个有序的元素列表，使用parallelStream时，应该避免使用limit,skip等操作，这些操作的成本将会很高
    }

    public static void limit() {
        List<User> list = getUserList(10);
        list.stream().limit(5).forEach(System.out::println);
    }

    public static void skip() {
        List<User> list = getUserList(10);
        list.stream().skip(5).forEach(System.out::println);
    }

    public static void reduce() {
        List<User> list = getUserList(100);
        Long sum = list.stream().map(User::getId).reduce(1000000L, (a, b) -> a + b);
        //没有初始值时，返回Option,为了防止列表为空
        Optional<Long> optionalLong = new ArrayList<User>().stream().map(User::getId).reduce((a, b) -> a + b);
        System.out.println("sum : " + sum);
        System.out.println("option sum : " + optionalLong.orElse(0L));
    }

    public static void peek() {
        List<User> list = getUserList(3);
        //peek 对每个元素执行操作并返回一个新的 Stream
        list.stream().distinct().peek(e -> System.out.println(e.getName())).forEach(System.out::println);
    }

    public static void flatMap() {
        //        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
//                "hello world welcome");
//
//        list.stream().map(item -> Arrays.stream(item.split(" ")))
//                .distinct().collect(Collectors.toList()).forEach(System.out::println);
//
//        System.out.println("---------- ");
//
//        list.stream().flatMap(item -> Arrays.stream(item.split(" ")))
//                .distinct().collect(Collectors.toList()).forEach(System.out::println);

        List<List<User>> userList = new ArrayList<>(4);

        for (int j = 0; j < 3; j++) {
            List<User> list = new ArrayList<>(5);
            for (int i = 1; i <= 3; i++) {
                User user = new User();
                user.setId((long) i);
                user.setName("test" + j + i);
                list.add(user);
            }
            userList.add(list);
        }
        userList.get(0).remove(0);
        userList.get(1).remove(1);
        userList.get(2).remove(2);

        userList.get(1).get(1).setName("test03");

        System.out.println(userList);

        List<User> collect = userList.stream().flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);

        //扁平化处理，取每个基本单位user的name，去重,最终获得5个不同的name
        List<String> stringList = userList.stream().flatMap(e -> e.stream().map(User::getName))
                .distinct().collect(Collectors.toList());
        System.out.println(stringList);
    }

    public static List<User> getUserList(int size) {
        List<User> list = new ArrayList<>(10);
        for (int i = 1; i <= size; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("test" + i);
            list.add(user);
        }
        return list;
    }
}