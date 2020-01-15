package com.luo.house.common.constants;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(0, "PRODUCT_NOT_EXIST"),
    PRODUCT_STOCK_ERROR(1, "PRODUCT_STOCK_ERROR"),
    ORDER_NOT_EXIST(2, "ORDER_NOT_EXIST"),
    ORDER_DETAIL_NOT_EXIST(3, "ORDER_DETAIL_NOT_EXIST"),
    UPDATE_ERROR(4, "UPDATE_ERROR"),
    PARAM_ERROR(5, "PARAM_ERROR"),
    CART_EMPTY(6, "CART_EMPTY"),
    ORDER_OWNER_ERROR(7, "ORDER_OWNER_ERROR"),
    //token
    ILLEGAL_ARG(8, "ILLEGAL_ARG"),
    REPEATABLE_OPERATION(9, "REPEATABLE_OPERATION"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
