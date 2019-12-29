package com.luo.house.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {

    @JsonProperty(value = "id")
    private String productId;
    @JsonProperty(value = "name")
    private String productName;

    @JsonProperty(value = "icon")
    private String productIcon;
    @JsonProperty(value = "description")
    private String productDescription;


    @JsonProperty(value = "price")
    private BigDecimal productPrice;
}
