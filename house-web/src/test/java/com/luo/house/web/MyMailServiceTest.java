package com.luo.house.web;

import com.luo.house.biz.service.MyMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyMailService.class})
public class MyMailServiceTest {

    @Resource
    private MyMailService myMailService;

    @Test
    public void testSimpleMail() {
        myMailService.sendSimpleMail("2084267015@qq.com", "this is text mail", "text: a simple mail和");
    }

    @Test
    public void testHtmlMail() throws MessagingException {
        String content = "<html><body><p>halo world html 好的</p></body></html>";
        myMailService.sendHtmlMail("2084267015@qq.com", "this is html mail", content);
    }

    @Test
    public void testOneFileMail() throws MessagingException {
        String content = "this is a mail with file";
        String filePath = "F:\\迅雷下载\\Java深入微服务原理改造房产销售平台\\课程资料\\各种流程图.zip";
        myMailService.sendFileMail("2084267015@qq.com", "this is  mail with file 附件", content, filePath);
    }

    @Test
    public void testFilesMail() throws MessagingException {
        String content = "this is a mail with file";
        String filePath = "F:\\迅雷下载\\Java深入微服务原理改造房产销售平台\\课程资料\\各种流程图.zip";
        myMailService.sendFilesMail("2084267015@qq.com", "this is  mail with file 附件", content, filePath);
    }

    @Test
    public void testInlineMail() throws MessagingException {
        String filePath = "F:\\迅雷下载\\Java深入微服务原理改造房产销售平台\\课程资料\\本课程微服务技术选型.png";
        String rscId = "test001";
        String content = "<html><body> halo world html img: <img src=\'cid:" + rscId + " \'" + "</img>" + "</body></html>";
        myMailService.sendInlineResourceMail("2084267015@qq.com", " mail with file inline 图片", content, filePath, rscId);
    }

}
