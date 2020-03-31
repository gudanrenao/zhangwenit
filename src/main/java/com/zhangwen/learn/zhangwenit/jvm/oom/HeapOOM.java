package com.zhangwen.learn.zhangwenit.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 堆内存溢出测试
 * @Author ZWen
 * @Date 2020/3/24 1:47 PM
 * @Version 1.0
 **/
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}