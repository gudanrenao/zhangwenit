package com.zhangwen.learn.zhangwenit.concurrent.collection.DelayQueue;

import com.zhangwen.learn.zhangwenit.common.dto.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Description 模拟微信支付异步通知，如果第一次通知没有响应，间隔5秒进行第二次通知，如果第二次
 * 通知依然没有响应，间隔10秒进行第三次通知
 * @Author ZWen
 * @Date 2019/4/10 6:03 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/intervalNotification")
public class IntervalNotification3TimesDemo {

    private static final Logger logger = LoggerFactory.getLogger(IntervalNotification3TimesDemo.class);

    private static final DelayQueue<UserDelayDemo> userDelayQueue = new DelayQueue<>();

    public IntervalNotification3TimesDemo() {
        send();
    }

    @PostMapping("/notification")
    public ResponseVO notification(@RequestBody User user) {
        //id为多少，代表重复发送几次，便于测试
        user.setTimes(1);
        userDelayQueue.add(new UserDelayDemo(user, System.currentTimeMillis()));
        return ResponseVO.buildSuccess();
    }

    private void send() {
        System.out.println("IntervalNotification work running.....");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            while (true) {
                try {

                    UserDelayDemo demo = userDelayQueue.take();

                    if (demo.getUser().getId() == 1) {
                        logger.info("send success first time: " + demo);
                        continue;
                    }
                    if (demo.getUser().getTimes() == 1) {
                        logger.error("send fail first time: " + demo);
                    }

                    if (demo.getUser().getId() == 2 && demo.getUser().getTimes() == 2) {
                        logger.info("send success second time: " + demo);
                        continue;
                    }
                    if (demo.getUser().getTimes() == 2) {
                        logger.error("send fail second time: " + demo);
                    }

                    if (demo.getUser().getId() == 3 && demo.getUser().getTimes() == 3) {
                        logger.info("send success third time: " + demo);
                        continue;
                    }
                    if (demo.getUser().getTimes() == 3) {
                        logger.error("send fail third time: " + demo);
                    }


                    //根据已发送次数，判断是否再次发送以及再次发送时的延迟时间
                    if (demo.getUser().getTimes() < 3) {
                        switch (demo.getUser().getTimes()) {
                            case 1:
                                //间隔5s
                                demo.setExpiredTime(System.currentTimeMillis() + 5 * 1000);
                                break;
                            case 2:
                                //间隔10s
                                demo.setExpiredTime(System.currentTimeMillis() + 10 * 1000);
                                break;
                            default:
                                logger.error("---------异常---------- times > 3");
                                continue;
                        }
                        demo.getUser().setTimes(demo.getUser().getTimes() + 1);
                        //重新添加到延迟队列中
                        userDelayQueue.offer(demo);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    static class UserDelayDemo implements Delayed {

        private final User user;
        /**
         * 过期时间，单位/毫秒
         */
        private long expiredTime;

        public UserDelayDemo(User user, long expiredTime) {
            this.user = user;
            this.expiredTime = expiredTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expiredTime - System.currentTimeMillis(), MILLISECONDS);
        }

        /**
         * 按到期时间从小到大排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            UserDelayDemo that = (UserDelayDemo) o;
            if (this.expiredTime > that.expiredTime) {
                return 1;
            } else if (this.expiredTime < that.expiredTime) {
                return -1;
            }
            return 0;
        }

        public User getUser() {
            return user;
        }

        public void setExpiredTime(long expiredTime) {
            this.expiredTime = expiredTime;
        }

        @Override
        public String toString() {
            return "Delay{" +
                    "user=" + user +
                    ", expiredTime=" + expiredTime +
                    '}';
        }
    }

    static class User {

        private Long id;

        private String name;
        /**
         * 当前是第几次发送
         */
        private int times;

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", times=" + times +
                    '}';
        }
    }
}