package com.luo.house.common.model;

import com.luo.house.common.constants.OrderStatus;
import com.luo.house.common.constants.PayStatus;
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
    private Integer orderStatus = OrderStatus.NEW.getCode();
    private Integer payStatus = PayStatus.WAIT.getCode();

    private Date createTime;
    private Date updateTime;

    //为了展示
    /*@Transient
    private List<OrderDetail> orderDetailList;*/
}
