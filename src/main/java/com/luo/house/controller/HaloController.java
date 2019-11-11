package com.luo.house.controller;

import com.luo.house.common.model.User;
import com.luo.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HaloController {
    @Autowired
    UserService userService;

    @GetMapping("halo")
    public String hello(ModelMap modelMap) {
        List<User> userList = userService.getUsers();
        User user = userList.get(0);
        modelMap.put("user", user);

        return "hello";
    }
}
