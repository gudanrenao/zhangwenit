package com.zhangwen.learn.zhangwenit.mail.service.impl;

import com.zhangwen.learn.zhangwenit.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("793863709@qq.com", "这是一封简单邮件", "大家好，这是我的第一封邮件！");
    }

    @Test
    public void sendHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "    <h1>dsdsdsdsdsd邮件!</h1>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("793863709@qq.com", "这是一封HTML邮件", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath1 = "d:\\learn\\zhangwenit\\src\\main\\resources\\static\\img\\ceshi.png";
        String filePath2 = "https://cdn.lepluslife.com/lepluslife/lepluslife.jpg";

        mailService.sendAttachmentsMail("793863709@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath1, filePath2);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String imgPath = "https://cdn.lepluslife.com/lepluslife/lepluslife.jpg";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";

        mailService.sendInlineResourceMail("793863709@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", 1234);
        context.setVariable("userName", "张文");
        mailService.sendTemplateMail("793863709@qq.com", "主题：这是模板邮件",
                "/email/template", context);
    }
}