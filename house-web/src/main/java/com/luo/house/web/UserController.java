package com.luo.house.web;

import com.luo.house.biz.service.UserService;
import com.luo.house.common.model.User;
import com.luo.house.common.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("accounts/register")
    public String accountRegister(User account, ModelMap modelMap) {

        if (account == null || account.getName() == null) {
            return "/user/account/register";//到注册页
        }
        //验证
        ResultMsg resultMsg = UserHelper.validate(account);
        if (resultMsg.isSuccess() && userService.addAccount(account)) {
            return "/user/account/registerSubmit";
        } else {
            return "rediret:account/register?" + resultMsg.asUrlParams();
        }


        return null;

    }
}
