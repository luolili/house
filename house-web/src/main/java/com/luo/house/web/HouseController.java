package com.luo.house.web;

import com.luo.house.biz.service.AgencyService;
import com.luo.house.biz.service.HouseService;
import com.luo.house.common.model.House;
import com.luo.house.common.model.UserMsg;
import com.luo.house.common.page.PageData;
import com.luo.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HouseController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private AgencyService agencyService;

    @PostMapping("house/list")
    public String list(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
        PageData<House> pageData = houseService.queryHouse(query, PageParams.build(pageNum, pageSize));
        modelMap.put("ps", pageData);
        modelMap.put("vo", query);
        return "/house/list";
    }

    @PostMapping("house/detail")
    public String detail(Long id, ModelMap modelMap) {
        House house = houseService.queryOneHouse(id);
        if (house.getUserId() != null && house.getUserId().equals(0)) {
            modelMap.put("agency", agencyService.getAgencyDetail(house.getUserId()));

        }
        modelMap.put("house", house);
        return "/house/detail";
    }

    @PostMapping("house/leaveMsg")
    public String leaveMsg(UserMsg userMsg, ModelMap modelMap) {

        houseService.addUserMsg(userMsg);
        return "/house/detail";
    }

}