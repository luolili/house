package com.luo.house.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luo.house.biz.dto.OrderDTO;
import com.luo.house.biz.exception.SellException;
import com.luo.house.biz.service.CategoryService;
import com.luo.house.biz.service.OrderService;
import com.luo.house.biz.service.ProductService;
import com.luo.house.common.constants.ResultEnum;
import com.luo.house.common.model.OrderDetail;
import com.luo.house.common.result.ResultVO;
import com.luo.house.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("buyer/order")
public class BuyerOrderCtr {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;

    @GetMapping("list")
    public ResultVO getAll() {

        return ResultVO.success(null);
    }

    @GetMapping("create")
    public ResultVO create(@Valid OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> detailList = convert(orderForm, orderDTO);
        orderDTO.setOrderDetailList(detailList);
        if (CollectionUtils.isEmpty(detailList)) {
            log.info("购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO save = orderService.save(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", save.getOrderId());

        return ResultVO.success(map);
    }

    private List<OrderDetail> convert(@Valid OrderForm orderForm, OrderDTO orderDTO) {
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAdress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetail> res = null;
        try {
            res = mapper.readValue(orderForm.getItems(), new TypeReference<List<OrderDetail>>() {
            });
        } catch (IOException e) {
            log.info("转换错误");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        return res;
    }

    @GetMapping("list")
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
