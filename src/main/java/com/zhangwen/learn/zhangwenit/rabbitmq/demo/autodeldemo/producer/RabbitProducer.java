//package com.zhangwen.learn.zhangwenit.rabbitmq.demo.autodeldemo.producer;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Description
// * @Author ZWen
// * @Date 2019/8/15 3:38 PM
// * @Version 1.0
// **/
//@Component
//public class RabbitProducer {
//
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void stringSend() {
//        Date date = new Date();
//        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
//        System.out.println("[string] send msg:" + dateString);
//        // 第一个参数为刚刚定义的队列名称
//        this.rabbitTemplate.convertAndSend("string", dateString);
//    }
//}