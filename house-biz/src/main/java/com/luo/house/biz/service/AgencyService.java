package com.luo.house.biz.service;

import com.luo.house.biz.mapper.AgencyMapper;
import com.luo.house.common.model.Agency;
import com.luo.house.common.model.User;
import com.luo.house.common.page.PageData;
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

    public PageData<User> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        Long count = agencyMapper.selectAgentCount(new User());
        return PageData.build(agents, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    public Agency getAgency(Integer id) {
        Agency agency = new Agency();
        agency.setId(id);
        List<Agency> agencies = agencyMapper.select(agency);
        if (agencies.isEmpty()) {
            return null;
        }
        return agencies.get(0);
    }

    public List<Agency> getAllAgency() {
        return agencyMapper.select(new Agency());
    }

    public int add(Agency agency) {
        return agencyMapper.insert(agency);
    }

    public void sendMsg(User agent, String msg, String name, String email) {
        // TODO Auto-generated method stub

    }
}