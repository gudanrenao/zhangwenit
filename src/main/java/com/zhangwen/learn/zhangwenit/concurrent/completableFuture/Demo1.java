package com.zhangwen.learn.zhangwenit.concurrent.completableFuture;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/3/9 7:19 PM
 * @Version 1.0
 **/
public class Demo1 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenApply(result -> {
            return result + " world!";
        }).thenAccept(System.out::println);


        Stream.of(1,2,3,4,5,6,7,8,9)
        .flatMapToDouble(DoubleStream::of)
                .filter(e -> {
                    System.out.println("filter: " + e);
                    return true;
                })
        .forEach(System.out::println);

        Mono<String> mono = Mono.fromSupplier(() -> {
            return "hello world";
        });
    }
}