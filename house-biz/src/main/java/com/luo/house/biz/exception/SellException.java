package com.luo.house.biz.exception;

import com.luo.house.common.constants.ResultEnum;

public class SellException extends RuntimeException {

    private Integer code;
    private String msg;

    public SellException() {
    }

    public SellException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
    }

}
