package com.luo.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HomeApp {
    public static void main(String[] args) {

        SpringApplication.run(HomeApp.class);
    }
}
