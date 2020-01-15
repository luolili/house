package com.luo.house.web;

import com.luo.house.biz.dto.OrderDTO;
import com.luo.house.biz.exception.SellException;
import com.luo.house.biz.service.CategoryService;
import com.luo.house.biz.service.OrderService;
import com.luo.house.biz.service.ProductService;
import com.luo.house.common.constants.ResultEnum;
import com.luo.house.common.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 后端用freemarker
 */
@Slf4j
@Controller
@RequestMapping("seller/order")
public class SellerOrderCtr {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;

    @GetMapping("list")
    public ModelAndView getAll(Integer page, Integer size) {

        PageRequest req = new PageRequest(page - 1, size);
        Page<OrderDTO> dtos = orderService.getAll(req);
        Map<String, Object> map = new HashMap<>();
        map.put("orderDTOPage", dtos.getContent());
        ModelAndView mv = new ModelAndView("order/list", map);
        return mv;
    }


    @GetMapping("list1")
    public ResultVO list(@RequestParam String openid,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size) {
        if (StringUtils.isBlank(openid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> dtos = orderService.getAll(request, openid);
        return ResultVO.success(dtos.getContent());
    }

    @GetMapping("detail")
    public ResultVO detail(@RequestParam String openid,
                           @RequestParam() String orderId) {
        OrderDTO res = checkOrderBuyer(openid, orderId);
        return ResultVO.success(res);
    }

    @GetMapping("cancel")
    public ResultVO cancel(@RequestParam String openid,
                           @RequestParam() String orderId) {
        OrderDTO orderDTO = checkOrderBuyer(openid, orderId);
        orderDTO.setOrderId(orderId);
        orderService.cancel(orderDTO);
        return ResultVO.success("");
    }

    private OrderDTO checkOrderBuyer(@RequestParam String openid, @RequestParam String orderId) {
        if (StringUtils.isBlank(openid) || StringUtils.isBlank(orderId)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO res = orderService.getOne(orderId);
        if (res != null && openid.equalsIgnoreCase(res.getBuyerOpenid())) {
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return res;
    }
}
