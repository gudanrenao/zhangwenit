package com.zhangwen.learn.zhangwenit.reactive.java9_reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/3/1 5:17 PM
 * @Version 1.0
 **/
public class SubmissionPublisherTest {

    public static void main(String[] args) throws InterruptedException {

        //try java7后的语法，最后会执行finally
        try (SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<>()) {
            submissionPublisher.subscribe(new MySubscriber("demo001", 1000));
            submissionPublisher.subscribe(new MySubscriber("demo002", 2000));
            submissionPublisher.subscribe(new MySubscriber("demo003", 3000));

            submissionPublisher.consume(value -> System.out.printf("consume current thread[%s] value[%s] \n",
                    Thread.currentThread().getName(), value));

            submissionPublisher.submit(1000);
        }

        Thread.currentThread().join(5000);
    }


    private static class MySubscriber implements Flow.Subscriber<Integer> {

        private final String name;
        private final long millis;

        private Flow.Subscription subscription;

        private MySubscriber(String name, long millis) {
            this.name = name;
            this.millis = millis;
        }

        /**
         * Method invoked prior to invoking any other Subscriber
         * methods for the given Subscription. If this method throws
         * an exception, resulting behavior is not guaranteed, but may
         * cause the Subscription not to be established or to be cancelled.
         *
         * <p>Typically, implementations of this method invoke {@code
         * subscription.request} to enable receiving items.
         *
         * @param subscription a new subscription
         */
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            try {
                Thread.sleep(this.millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("current thread[%s] subscriber[%s] Subscription[%s] onSubscribe\n",
                    Thread.currentThread().getName(), this.name, subscription);
            this.subscription = subscription;
            subscription.request(1);
        }

        /**
         * Method invoked with a Subscription's next item.  If this
         * method throws an exception, resulting behavior is not
         * guaranteed, but may cause the Subscription to be cancelled.
         *
         * @param item the item
         */
        @Override
        public void onNext(Integer item) {
            System.out.printf("current thread[%s] subscriber[%s] Subscription[%s] onNext\n",
                    Thread.currentThread().getName(), this.name, subscription);
            subscription.request(1);
        }

        /**
         * Method invoked upon an unrecoverable error encountered by a
         * Publisher or Subscription, after which no other Subscriber
         * methods are invoked by the Subscription.  If this method
         * itself throws an exception, resulting behavior is
         * undefined.
         *
         * @param throwable the exception
         */
        @Override
        public void onError(Throwable throwable) {
            System.out.printf("current thread[%s] subscriber[%s] error: %s\n",
                    Thread.currentThread().getName(), this.name, throwable.getMessage());
            throwable.printStackTrace();
        }

        /**
         * Method invoked when it is known that no additional
         * Subscriber method invocations will occur for a Subscription
         * that is not already terminated by error, after which no
         * other Subscriber methods are invoked by the Subscription.
         * If this method throws an exception, resulting behavior is
         * undefined.
         */
        @Override
        public void onComplete() {
            System.out.printf("current thread[%s] subscriber[%s] completed!!!\n",
                    Thread.currentThread().getName(), this.name);
        }
    }
}