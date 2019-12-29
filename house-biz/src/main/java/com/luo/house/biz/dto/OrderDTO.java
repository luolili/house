package com.luo.house.biz.dto;

import com.luo.house.common.model.OrderDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    private String orderName;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal buyerAmount;
    private Integer orderStatus;
    private Integer payStatus;

    private Date createTime;
    private Date updateTime;

    //为了展示
    @Transient
    private List<OrderDetail> orderDetailList;
}
