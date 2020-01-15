package com.luo.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
/* @ComponentScan(value = "com") */
public class HomeApp {
    //private static final Logger logger = LoggerFactory.getLogger(HomeApp.class);
    public static void main(String[] args) {


        SpringApplication.run(HomeApp.class);
    }
}
