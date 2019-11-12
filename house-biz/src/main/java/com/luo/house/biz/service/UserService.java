package com.luo.house.biz.service;

import com.luo.house.biz.mapper.UserMapper;
import com.luo.house.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getUsers() {

        return userMapper.selectUsers();
    }
}
