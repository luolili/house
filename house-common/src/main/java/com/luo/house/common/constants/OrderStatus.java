package com.luo.house.common.constants;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW(0, "新订单"),
    finished(1, "完结"),
    CANCEL(2, "取消"),
    ;

    private Integer code;
    private String msg;

    OrderStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
