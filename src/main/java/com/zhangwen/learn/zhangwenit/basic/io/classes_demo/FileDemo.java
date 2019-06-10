package com.zhangwen.learn.zhangwenit.basic.io.classes_demo;

import joptsimple.internal.Strings;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;

/**
 * @Description {@link File} demo
 * @Author ZWen
 * @Date 2019/6/5 11:50 AM
 * @Version 1.0
 **/
public class FileDemo {

    public static void main(String[] args) {
        listTest();
    }

    private static void listTest() {
        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/basic/io/file");

        String[] list = file.list();
        Assert.notEmpty(list,"list is empty");
        //排序
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        Arrays.asList(list).forEach(System.out::println);
        System.out.println("\n=================================\n");
        //按照条件过滤
        String[] filterList1 = file.list((f, name) -> name.endsWith(".txt"));
        Arrays.asList(filterList1).forEach(System.out::println);
        System.out.println("\n=================================\n");
        //过滤出文件夹
        String[] filterList2 = file.list((f, name) -> new File(f, name).isDirectory());
        Arrays.asList(filterList2).forEach(System.out::println);
        System.out.println("\n=================================\n");

    }
}