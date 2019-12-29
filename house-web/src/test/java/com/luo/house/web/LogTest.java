package com.luo.house.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTest {

    @Test
    public void test() {
        String name = "u";
        log.info("name:[{}]", name);
    }
}
