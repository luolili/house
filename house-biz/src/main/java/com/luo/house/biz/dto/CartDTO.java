package com.luo.house.biz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDTO {

    private String productId;
    private Integer productQantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQantity) {
        this.productId = productId;
        this.productQantity = productQantity;
    }
}
