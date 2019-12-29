package com.luo.house.common.constants;

import lombok.Getter;

@Getter
public enum ProductStatus {
    UP(0),
    DOWN(1),
    ;

    private Integer code;

    ProductStatus(Integer code) {
        this.code = code;
    }
}
