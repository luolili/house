package com.luo.house.web;

import com.luo.house.biz.service.TokenService;
import com.luo.house.common.result.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/token")
public class ApiIdempotentCtr {

    @Resource
    TokenService tokenService;

    @GetMapping
    public ResultVO token() {
        return tokenService.createToken();

    }
}
