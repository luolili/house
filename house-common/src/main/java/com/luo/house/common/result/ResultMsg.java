package com.luo.house.common.result;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data
public class ResultMsg {

    private static final String errMsgKey = "errorMsg";
    private static final String successMsgKey = "successMsg";
    private String errorMsg;
    private String successMsg;

    public ResultMsg errorMsg(String msg) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setErrorMsg(msg);
        return resultMsg;
    }

    public ResultMsg successMsg(String msg) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setSuccessMsg(msg);
        return resultMsg;
    }

    public Map<String, String> asMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put(errMsgKey, errorMsg);
        map.put(successMsgKey, successMsg);
        return map;

    }


}
