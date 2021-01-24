package com.zhangwen.learn.zhangwenit.guava.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Description 布隆过滤器demo
 * @Author ZWen
 * @Date 2021/1/24 4:01 PM
 * @Version 1.0
 **/
public class BloomFilterDemo {

    public static void main(String[] args) {

        final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000000, 0.001);

        int size = 1000000;

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        int newSize = 100000;

        int errSize = 0;
        for (int i = size ; i < newSize + size; i++) {
            if(bloomFilter.mightContain(i)){
                System.err.println("误判了。。。 " + i);
                errSize++;
            }
        }

        System.err.println("误判率 = " + (errSize * 1.0 / newSize));
    }
}