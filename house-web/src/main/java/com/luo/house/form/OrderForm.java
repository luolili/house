package com.luo.house.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class OrderForm {
    private String orderId;
    //买家
    @NotBlank(message = "买家必填")
    private String name;
    private String phone;
    private String adress;
    private String openid;
    private String items;//购物车信息
}
