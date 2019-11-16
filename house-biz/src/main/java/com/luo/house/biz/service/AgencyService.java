package com.luo.house.biz.service;

import com.luo.house.biz.mapper.AgencyMapper;
import com.luo.house.common.model.User;
import com.luo.house.common.page.PageParams;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;
    @Value("${file.prefix}")
    private String filePrefix;

    public User getAgencyDetail(Long userId) {

        User user = new User();
        user.setId(userId);
        user.setType(2);
        List<User> userList = agencyMapper.selectAgent(user, PageParams.build(1, 1));
        setImg(userList);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;

    }

    private void setImg(List<User> userList) {
        userList.forEach(i -> {
            i.setAvator(filePrefix + i.getAvator());
        });
    }
}