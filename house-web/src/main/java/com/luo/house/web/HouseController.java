package com.luo.house.web;

import com.google.common.collect.Lists;
import com.luo.house.biz.service.HouseService;
import com.luo.house.common.model.Communinty;
import com.luo.house.common.model.House;
import com.luo.house.common.page.PageParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("house/list")
    public String list(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
        houseService.queryHouse(query, PageParams.build(pageNum, pageSize));

        return "";
    }

}