package com.luo.house.web;

import com.luo.house.biz.service.MyMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyMailService.class})
public class MyMailServiceTest {

    @Resource
    private MyMailService myMailService;

    @Test
    public void testSimpleMail() {
        myMailService.sendSimpleMail("2084267015@qq.com", "this is text mail", "text: a simple mail");
    }
}
