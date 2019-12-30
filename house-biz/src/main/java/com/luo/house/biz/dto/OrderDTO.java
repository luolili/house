package com.luo.house.biz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luo.house.common.model.OrderDetail;
import com.luo.house.common.utils.DateToLongSerializer;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//当字段的值为null时，不返回给前台，局部配置：不建议
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    //转为 秒，jackson
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    //为了展示
    @Transient
    private List<OrderDetail> orderDetailList;
}
