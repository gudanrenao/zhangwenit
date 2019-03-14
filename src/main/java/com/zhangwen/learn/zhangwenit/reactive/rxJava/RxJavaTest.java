package com.zhangwen.learn.zhangwenit.reactive.rxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/3/2 10:45 AM
 * @Version 1.0
 **/
public class RxJavaTest {

    private static final Logger logger = LoggerFactory.getLogger(RxJavaTest.class);
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            logger.error("create subscribe begin....");
            emitter.onNext("123");
            emitter.onNext("789");
//            emitter.onNext("zz");
//            emitter.onNext("0");
            emitter.onComplete();
        }).map(s -> {
            logger.error("map String to Integer : source value[{}]", s);
            return Integer.valueOf(s);
        }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                logger.error("subscribeOn onSubscribe [{}]", d);
            }

            @Override
            public void onNext(Integer s) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.error("subscribeOn onNext [{}]", s);
            }

            @Override
            public void onError(Throwable e) {
                logger.error("subscribeOn onError [{}]", e.getMessage());
            }

            @Override
            public void onComplete() {
                logger.error("subscribeOn onComplete!!!!");
            }
        });

        Thread.currentThread().join(5000);
    }
}