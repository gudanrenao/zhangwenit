package com.zhangwen.learn.zhangwenit.rabbitmq.demo.autodeldemo.controller;

import com.zhangwen.learn.zhangwenit.common.dto.ResponseVO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/8/26 3:16 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mq")
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/send")
    public ResponseVO send(@RequestParam String exchange, @RequestParam String routingKey, @RequestParam String msg) {
        MessageProperties properties = MessagePropertiesBuilder.newInstance()
                .setCorrelationId("" + System.currentTimeMillis())
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        Message message = new Message(msg.getBytes(), properties);
        rabbitTemplate.send(exchange, routingKey, message);
        return ResponseVO.buildSuccess();
    }
}