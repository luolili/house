package com.luo.house.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty(value = "name")
    private String categoryName;
    @JsonProperty(value = "type")
    private Integer categoryType;
    @JsonProperty(value = "foods")
    List<ProductInfoVO> productInfoVOList;
}
