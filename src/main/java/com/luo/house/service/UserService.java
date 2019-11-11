package com.luo.house.service;

import com.luo.house.common.model.User;
import com.luo.house.mapper.UserMapper;
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
