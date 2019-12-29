package com.luo.house.web;

import com.luo.house.biz.service.MailService;
import com.luo.house.biz.service.UserService;
import com.luo.house.common.constants.CommonConstants;
import com.luo.house.common.model.User;
import com.luo.house.common.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
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
    @ResponseBody
    public List<User> get() {
        mailService.sendMail("test", "00", "2084267015@qq.com");
        return userService.getUsers();
    }

    //异步发送email,因为发送 email 比较慢；发送email,点击link 激活
    @GetMapping("accounts/verify")
    public String verify(String key) {
        mailService.sendMail("test", "00", "2084267015@qq.com");
        boolean result = userService.enable(key);
        if (result) {
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        } else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败").asUrlParams();
        }
    }

    @GetMapping("accounts/signin")
    public String login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String target = req.getParameter("target");
        if (username == null || password == null) {
            req.setAttribute("target", target);
            return "redirect:/user/accounts/signin";
        }
        User user = userService.auth(username, password);
        if (user == null) {
            return "redirect:/user/accounts/signin?" + "target=" + target + "&username=" + username
                    + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        } else {
            //session 存放user info
            HttpSession session = req.getSession(true);//强制创建session
            session.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
            return StringUtils.isNoneBlank(target) ? "redirect:" + target : "redirect:/index";
        }

    }

    @GetMapping("accounts/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.invalidate();
        return "redirect:/index";

    }

    @GetMapping("accounts/profile")
    public String profile(User user, ModelMap modelMap, HttpServletRequest req) {
        if (user.getEmail() == null) {
            return "redirect:/user/accounts/profile";
        }
        //更新
        userService.updateUser(user, user.getEmail());
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        List<User> list = userService.getUserByQuery(newUser);
        req.getSession().setAttribute("user", list.get(0));
        return "redirect:/user/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();

    }
    //--


}
