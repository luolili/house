package com.luo.house.biz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

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

    //必须有这个配置，否则cannot connect port 25
    @Bean
    public JavaMailSender javaMailSender() {
        // 默认配置相关
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setUsername(from);
        javaMailSender.setPassword(password);

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
}
