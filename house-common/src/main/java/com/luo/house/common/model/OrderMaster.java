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
public class OrderMaster {

    @Id
    private String orderId;
    private String orderName;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal buyerAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private String productIcon;
    private Integer productStock;
    private Integer productStatus;//0正常1下架
    private Integer categoryType;
    private BigDecimal productPrice;


    private Date createTime;
    private Date updateTime;
}
