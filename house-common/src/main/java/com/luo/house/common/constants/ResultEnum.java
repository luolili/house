package com.luo.house.common.constants;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(0, "PRODUCT_NOT_EXIST"),
    PRODUCT_STOCK_ERROR(1, "PRODUCT_STOCK_ERROR"),
    ORDER_NOT_EXIST(1, "ORDER_NOT_EXIST"),
    ORDER_DETAIL_NOT_EXIST(1, "ORDER_DETAIL_NOT_EXIST"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
