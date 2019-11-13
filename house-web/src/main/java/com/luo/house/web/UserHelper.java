package com.luo.house.web;

import com.luo.house.common.model.User;
import com.luo.house.common.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

public class UserHelper {

    public static ResultMsg validate(User account) {
        if (StringUtils.isBlank(account.getEmail())) {
            return ResultMsg.errorMsg("email err");
        }
        if (StringUtils.isBlank(account.getName())) {
            return ResultMsg.errorMsg("name err");
        }

        if (StringUtils.isBlank(account.getPasswd()) || StringUtils.isBlank(account
                .getConfirmPasswd()) || (!StringUtils.equals(account.getPasswd(), account.getConfirmPasswd()))) {
            return ResultMsg.errorMsg("passwd err");
        }
        return ResultMsg.successMsg("");

    }
}
