package com.luo.house.controller;

import com.luo.house.common.model.User;
import com.luo.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("user")
    public List<User> getUsers() {
        List<User> userList = userService.getUsers();
        return userList;
    }
}
