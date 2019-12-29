package com.luo.house.common.constants;

import lombok.Getter;

@Getter
public enum PayStatus {
    WAIT(0, "未支付"),
    SUCCESS(1, "已支付"),
    ;

    private Integer code;
    private String msg;

    PayStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
