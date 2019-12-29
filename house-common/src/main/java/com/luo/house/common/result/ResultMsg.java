package com.luo.house.common.result;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Data
public class ResultMsg {

    private static final String errMsgKey = "errorMsg";
    private static final String successMsgKey = "successMsg";
    private String errorMsg;
    private String successMsg;

    public boolean isSuccess() {
        return errorMsg == null;
    }


    public static ResultMsg errorMsg(String msg) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setErrorMsg(msg);
        return resultMsg;
    }

    public static ResultMsg successMsg(String msg) {
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

    public String asUrlParams() {
        Map<String, String> map = asMap();
        Map<String, String> newMap = Maps.newHashMap();

        map.forEach((k, v) -> {
            if (k != null) {
                try {
                    newMap.put(k, URLEncoder.encode(v, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(newMap);
    }


}
