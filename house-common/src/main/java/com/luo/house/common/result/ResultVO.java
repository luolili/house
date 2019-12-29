package com.luo.house.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    private T data;
    private String msg;
    private String code;

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(data, "success", "200");
    }


}
