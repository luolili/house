package com.luo.house.biz.service;

import com.luo.house.biz.mapper.UserMapper;
import com.luo.house.common.model.User;
import com.luo.house.common.utils.HashUtils;
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

    public boolean addAccount(User account) {

        String encryPassword = HashUtils.encryPassword(account.getPasswd());

        account.setPasswd(encryPassword);


    }
}
