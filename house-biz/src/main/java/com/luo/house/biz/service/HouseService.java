package com.luo.house.biz.service;

import com.google.common.collect.Lists;
import com.luo.house.biz.mapper.HouseMapper;
import com.luo.house.common.model.Community;
import com.luo.house.common.model.House;
import com.luo.house.common.page.PageData;
import com.luo.house.common.page.PageParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public PageData<House> queryHouse(House query, PageParams pageParams) {
        List<House> houseList = Lists.newArrayList();

        if (StringUtils.isNotEmpty(query.getName())) {
            Community community = new Community();
            community.setName(query.getName());

            List<Community> communityList = houseMapper.selectCommunity(community);

            if (!communityList.isEmpty()) {
                query.setCommunityId(communityList.get(0).getId());
            }
        }
        houseList = queryAndSetImg(query, pageParams);

        Long count = houseMapper.selectPageCount(query);
        return PageData.build(houseList, count, pageParams.getPageSize(), pageParams.getPageNum());


    }

    @Value("${file.prefix}")
    private String filePrefix;

    private List<House> queryAndSetImg(House query, PageParams pageParams) {

        List<House> houses = houseMapper.selectPageHouses(query, pageParams);
        houses.forEach(h -> {
            h.setFirstImg(filePrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> filePrefix + img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(fp -> filePrefix + fp).collect(Collectors.toList()));

        });
        return houses;
    }
}
