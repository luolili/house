package com.luo.house.common.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private String productIcon;
    private Integer productStock;
    private Integer productStatus;//0正常1下架
    private Integer categoryType;
    private BigDecimal productPrice;


    private Date createTime;
    private Date updateTime;
}
