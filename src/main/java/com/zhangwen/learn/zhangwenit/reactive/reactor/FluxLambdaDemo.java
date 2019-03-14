package com.zhangwen.learn.zhangwenit.reactive.reactor;

import com.zhangwen.learn.zhangwenit.util.PrintUtils;
import reactor.core.publisher.Flux;

import java.util.Random;

/**
 * @Description spring封装的对reactive streams 标准 的实现框架
 * @Author ZWen
 * @Date 2019/3/2 4:18 PM
 * @Version 1.0
 **/
public class FluxLambdaDemo {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Flux.just(1, 2, 3).map(value -> {
            if (value == 2 && random.nextInt(3) == 2) {
                throw new RuntimeException("value can be not 2");
            }
            return 100 + value;
        }).subscribe(PrintUtils::println, PrintUtils::println,
        () -> PrintUtils.println("subscribe is done!"));
    }
}