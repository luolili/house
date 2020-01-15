package com.luo.house.biz.service;

import com.luo.house.biz.exception.SellException;
import com.luo.house.common.constants.CommonConstants;
import com.luo.house.common.constants.ResultEnum;
import com.luo.house.common.result.ResultVO;
import com.luo.house.common.utils.JedisUtil;
import com.luo.house.common.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * token+redis 实现接口幂等性
 */
@Service
public class TokenService {

    private static final String TOKEN_NAME = "token";
    @Autowired
    private JedisUtil jedisUtil;

    public ResultVO createToken() {
        String str = RandomUtil.UUID32();
        StrBuilder token = new StrBuilder();
        token.append(CommonConstants.Redis.TOKEN_PREFIX).append(str);
        jedisUtil.set(token.toString(), token.toString(), CommonConstants.Redis.EXPIRE_TIME_MINUTE);

        return ResultVO.success(token.toString());

    }

    public void checkToken(HttpServletRequest req) {
        String token = req.getHeader(TOKEN_NAME);

        if (StringUtils.isBlank(token)) {

            token = req.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                throw new SellException(ResultEnum.ILLEGAL_ARG);
            }

            if (!jedisUtil.exist(token)) {
                throw new SellException(ResultEnum.REPEATABLE_OPERATION);

            }

            Long res = jedisUtil.del(token);
            if (res < 0) {
                throw new SellException(ResultEnum.REPEATABLE_OPERATION);
            }
        }


    }
}
