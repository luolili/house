package com.luo.house.web;

import com.luo.house.biz.service.UserService;
import com.luo.house.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("accounts/register")
    public void accountRegister(User account, ModelMap modelMap) {


    }
}
