package com.luo.house.web;

import com.luo.house.biz.service.AgencyService;
import com.luo.house.biz.service.HouseService;
import com.luo.house.common.model.Agency;
import com.luo.house.common.model.House;
import com.luo.house.common.model.UserMsg;
import com.luo.house.common.page.PageData;
import com.luo.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * spring actuator:
 * 1.http://localhost:8080/health
 * {
 * status: "UP",
 * mail: {
 * status: "UP",
 * location: "smtp.qq.com:-1"
 * },
 * diskSpace: {
 * status: "UP",
 * total: 123179659264,
 * free: 88003624960,
 * threshold: 10485760
 * },
 * db: {
 * status: "UP",
 * database: "MySQL",
 * hello: 1
 * }
 * }
 * <p>
 * 2.http://localhost:8080/beans
 * {
 * bean: "authActionInterceptor",
 * aliases: [ ],
 * scope: "singleton",
 * type: "com.luo.house.interceptor.AuthActionInterceptor",
 * resource: "file [F:/githubpro/house/house-web/target/classes/com/luo/house/interceptor/AuthActionInterceptor.class]",
 * dependencies: [ ]
 * }
 * <p>
 * 3.http://localhost:8080/autoconfig
 * 4.http://localhost:8080/dump 当前的运行状态
 * 5.http://localhost:8080/configprops
 * 6.http://localhost:8080/env
 * 7.http://localhost:8080/mappings
 * 8.http://localhost:8080/metrics
 * 9.http://localhost:8080/trace 访问记录 /info , /shutdown
 */
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

    @GetMapping("house/detail")
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
        return "/house/leaveMsg";
    }

    /**
     * 测试 mybatis executor
     */
    @GetMapping("agency/detail")
    @ResponseBody
    public String agency(Integer id) {
        Agency agency = agencyService.getAgency(id);
        return " v";
    }

}