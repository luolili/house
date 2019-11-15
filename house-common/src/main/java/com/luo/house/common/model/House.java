package com.luo.house.common.model;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class House {
    private Long id;
    private Integer type;
    private Integer price;
    private String name;
    private String images;
    private Integer area;
    private Integer beds;
    private Integer baths;
    private Double rating;
    private String remarks;
    private String properties;
    private String floorPlan;
    private String address;
    private String firstImg;
    private String tags;
    private Date createTime;
    private Integer cityId;
    private Integer communityId;
    private List<String> imageList = Lists.newArrayList();
    private List<String> floorPlanList = Lists.newArrayList();

    private List<MultipartFile> houseFiles;
    private List<MultipartFile> floorPlanFiles;
    private Long userId;

    private boolean bookmarked;
    private Integer status;
    private List<Long> ids;

}
