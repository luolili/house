package com.luo.house.jvmstudy.ch4;

import com.luo.house.jvmstudy.ch2.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * btrace默认是本地运行,被修改的字节码不会被还原
 *
 */
@RestController
@RequestMapping("ch4")
public class Ch4Controller {

    @RequestMapping("/arg1")
    public String test(@RequestParam("name") String name) {
        return name + "halo";
    }

    @RequestMapping("/arg2")
    public String test(@RequestParam("name") String name, @RequestParam("id") int id) {
        return name + "halo" + "," + id;
    }

    @RequestMapping("/exception")
    public String exception() {
        try {
            System.out.println("--");
            System.out.println(1 / 0);
            System.out.println("--");
        } catch (Exception e) {
//ex没有跑出来
        }
        return "halo";
    }

    @RequestMapping("/constructor")
    public User constructor(User user) {
        return user;
    }


}
