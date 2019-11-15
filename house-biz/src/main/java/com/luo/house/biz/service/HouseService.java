package com.luo.house.biz.service;

import com.luo.house.biz.mapper.HouseMapper;
import com.luo.house.common.model.House;
import com.luo.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public void queryHouse(House query, PageParams build) {


    }
}
