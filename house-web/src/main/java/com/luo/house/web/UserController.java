package com.luo.house.web;

import com.luo.house.biz.service.MailService;
import com.luo.house.biz.service.UserService;
import com.luo.house.common.model.User;
import com.luo.house.common.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    }

    @Autowired
    MailService mailService;

    @GetMapping("get")
    public List<User> get() {
        mailService.sendMail("test", "00", "2084267015@qq.com");
        return userService.getUsers();
    }

    @GetMapping("get")
    public String verify(String key) {
        mailService.sendMail("test", "00", "2084267015@qq.com");
        boolean result = userService.enable(key);

        if (result) {
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        } else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败").asUrlParams();
        }
    }



}
