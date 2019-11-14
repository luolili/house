package com.luo.house.biz.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import com.luo.house.biz.mapper.UserMapper;
import com.luo.house.common.model.User;
import com.luo.house.common.utils.BeanHelper;
import com.luo.house.common.utils.HashUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    FileService fileService;

    public List<User> getUsers() {

        return userMapper.selectUsers();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(User account) {
        String encryPassword = HashUtils.encryPassword(account.getPasswd());
        account.setPasswd(encryPassword);
        List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatorFile()));
        if (!CollectionUtils.isEmpty(imgList)) {
            account.setAvator(imgList.get(0));

        }
        BeanHelper.setDefaultProp(account, User.class);
        BeanHelper.onInsert(account);
        account.setEnable(0);
        userMapper.insert(account);
        mailService.registerNotify(account.getEmail());

        return true;

    }



    @Autowired
    MailService mailService;

    public boolean enable(String key) {
        return mailService.enable(key);
    }
}
