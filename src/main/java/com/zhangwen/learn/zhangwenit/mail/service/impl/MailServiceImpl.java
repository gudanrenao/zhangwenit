package com.zhangwen.learn.zhangwenit.mail.service.impl;

import com.zhangwen.learn.zhangwenit.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 发送邮件
 *
 * @author zhangwen at 2018-06-15 12:31
 **/
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * 发送简单邮件
     * 简单邮件抄送使用：message.copyTo(copyTo) 来实现。
     *
     * @param to      接受者邮箱
     * @param subject 主体
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送富文本邮件
     * 富文本邮件抄送使用：helper.addCc(cc) 来实现。
     *
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true 表示需要创建一个 multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath 附件路径（本地磁盘）或地址（网络地址）
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String... filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (String path : filePath) {
                if (path.startsWith("http")) {
                    URLDataSource urlDataSource = new URLDataSource(new URL(path));
                    helper.addAttachment(urlDataSource.getName(), urlDataSource);
                } else {
                    FileSystemResource file = new FileSystemResource(new File(path));
                    //添加多个附件可以使用多条 helper.addAttachment(fileName, file)
                    helper.addAttachment(file.getFilename(), file);
                }
            }

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("发送带附件的邮件时发生异常2！", e);
        }
    }

    /**
     * 发送带静态资源的邮件
     * 邮件中的静态资源一般就是指图片
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath 资源路径（本地磁盘）或地址（网络地址）
     * @param rscId   资源ID
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            if (rscPath.startsWith("http")) {
                URLDataSource source = new URLDataSource(new URL(rscPath));
                helper.addInline(rscId, source);
            } else {
                FileSystemResource res = new FileSystemResource(new File(rscPath));
                helper.addInline(rscId, res);
            }

            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常1！", e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("发送嵌入静态资源的邮件时发生异常2！", e);
        }
    }

    /**
     * 发送邮件模板
     *
     * @param to
     * @param subject
     * @param template 模板文件名称（路径）
     * @param context  模板中需替换的变量
     */
    @Override
    public void sendTemplateMail(String to, String subject, String template, Context context) {
        String content = templateEngine.process(template, context);
        sendHtmlMail(to, subject, content);
    }
}
