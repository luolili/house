package com.luo.house.biz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * 1.文本发送
 */
@Service
public class MyMailService {
    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.default-encoding}")
    private String defaultEncoding;

    //必须有这个配置，否则cannot connect port 25
    @Bean
    public JavaMailSender javaMailSender() {
        // 默认配置相关
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setUsername(from);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(defaultEncoding);

        // 认证相关
        Properties properties = new Properties();
        properties.setProperty("mail.host", mailHost);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender().send(message);
    }

    public void sendHtmlMail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender().send(message);
    }

    public void sendFileMail(String to, String subject, String text, String filePath) throws MessagingException {
        MimeMessage message = javaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        //spring.io
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String filename = file.getFilename();
        helper.addAttachment(filename, file);
        javaMailSender().send(message);
    }

    public void sendFilesMail(String to, String subject, String text, String filePath) throws MessagingException {
        MimeMessage message = javaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        //spring.io
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String filename = file.getFilename();
        helper.addAttachment(filename, file);
        helper.addAttachment(filename + "_test", file);
        javaMailSender().send(message);
    }

    public void sendInlineResourceMail(String to, String subject, String text, String rscPath, String rscId) throws MessagingException {
        MimeMessage message = javaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        //spring.io
        FileSystemResource resource = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, resource);//在正文 里面加图片
        javaMailSender().send(message);
    }


}
