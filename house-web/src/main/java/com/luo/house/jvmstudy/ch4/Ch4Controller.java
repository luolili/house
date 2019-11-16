package com.luo.house.jvmstudy.ch4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ch4")
public class Ch4Controller {

    @RequestMapping("/arg1")
    public String test(@RequestParam("name") String name) {
        return name + "halo";
    }
}
