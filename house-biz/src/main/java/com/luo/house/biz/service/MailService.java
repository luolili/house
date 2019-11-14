package com.luo.house.biz.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.luo.house.biz.mapper.UserMapper;
import com.luo.house.common.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class MailService {
    @Bean(name = "javaMailSender")
    public JavaMailSenderImpl javaMailSender() {
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

    @Autowired
    UserMapper userMapper;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String mailHost;
    private Cache<String, String> registerCache = CacheBuilder
            .newBuilder()
            .maximumSize(100)//并发量100
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, String>() {

                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    userMapper.delete(notification.getValue());
                }
            }).build();

    public void sendMail(String title, String url, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(title);
        mailMessage.setFrom(from);
        mailMessage.setTo(email);
        mailMessage.setText(url);
        javaMailSender().send(mailMessage);

    }

    @Value("${domain.name}")
    private String domainName;

    //异步发送email,因为发送 email 比较慢
    @Async
    public void registerNotify(String email) {

        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        String url = "http://" + domainName + "/accounts/verify?key" + randomKey;
        sendMail("test", url, email);
    }

    public boolean enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email)) {
            return false;
        }
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setEnable(1);
        userMapper.update(updateUser);
        registerCache.invalidate(key);
        return true;

    }
}
