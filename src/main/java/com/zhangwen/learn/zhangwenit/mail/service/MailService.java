package com.zhangwen.learn.zhangwenit.mail.service;

import org.thymeleaf.context.Context;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String... filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    void sendTemplateMail(String to, String subject, String template, Context context);
}
