package com.luo.house.biz.service;

import com.luo.house.biz.dto.CartDTO;
import com.luo.house.biz.dto.OrderDTO;
import com.luo.house.biz.dto.OrderMasterToOrderDTOConvertor;
import com.luo.house.biz.exception.SellException;
import com.luo.house.biz.repo.OrderDetailRepo;
import com.luo.house.biz.repo.OrderMasterRepo;
import com.luo.house.biz.repo.ProductInfoRepo;
import com.luo.house.common.constants.OrderStatus;
import com.luo.house.common.constants.PayStatus;
import com.luo.house.common.constants.ResultEnum;
import com.luo.house.common.model.OrderDetail;
import com.luo.house.common.model.OrderMaster;
import com.luo.house.common.model.ProductInfo;
import com.luo.house.common.utils.KeyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderMasterRepo orderMasterRepo;
    @Autowired
    private ProductInfoRepo productInfoRepo;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepo orderDetailRepo;


    public OrderDTO getOne(String id) {
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster master = orderMasterRepo.findOne(id);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> detailList = orderDetailRepo.findByOrderId(id);
        if (CollectionUtils.isEmpty(detailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setOrderDetailList(detailList);
        return orderDTO;

    }

    //分页：new PageRequest(0,2)
    public Page<OrderDTO> getAll(Pageable pageable, String buyerOpenid) {
        Page<OrderMaster> page = orderMasterRepo.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> dtoList = OrderMasterToOrderDTOConvertor.convert(page.getContent());
        PageImpl<OrderDTO> res = new PageImpl<OrderDTO>(dtoList, pageable, page.getTotalElements());
        return res;

    }

    public Page<OrderDTO> getAll(Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepo.findAll(pageable);
        List<OrderDTO> dtoList = OrderMasterToOrderDTOConvertor.convert(page.getContent());
        PageImpl<OrderDTO> res = new PageImpl<OrderDTO>(dtoList, pageable, page.getTotalElements());
        return res;

    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        //查询product
        List<OrderDetail> orderDetailList = genOrderDetail(orderDTO, orderId, totalPrice);
        //写入2条表
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterRepo.save(orderMaster);
        //减少库存
        decreaseStock(orderDetailList);
        return orderDTO;

    }

    @Transactional
    public List<OrderDetail> genOrderDetail(OrderDTO orderDTO, String orderId, BigDecimal totalPrice) {
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            String productId = orderDetail.getProductId();
            ProductInfo productInfo = productInfoRepo.findOne(productId);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer productQuantity = orderDetail.getProductQuantity();
            //计算total price
            totalPrice = orderDetail.getProductPrice().multiply(new BigDecimal(String.valueOf(productQuantity))).add(totalPrice);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productId);
            //orderDetail.setProductQuantity(productQuantity);//传过来的
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepo.save(orderDetail);
        }
        return orderDetailList;
    }

    @Transactional
    public void decreaseStock(List<OrderDetail> orderDetailList) {
        List<CartDTO> cartDTOList;
        cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())
                ).collect(Collectors.toList());
        productService.decrStock(cartDTOList);
    }

    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //查询订单状态
        if (OrderStatus.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMaster.setOrderStatus(OrderStatus.CANCEL.getCode());
            OrderMaster save = orderMasterRepo.save(orderMaster);
            if (save == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
            if (CollectionUtils.isEmpty(orderDetailList)) {
                throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
            }

            //返还stock
            List<CartDTO> cartDTOList = orderDetailList.stream()
                    .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                    .collect(Collectors.toList());
            productService.addStock(cartDTOList);
            //已支付，退款：
            if (orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())) {
                //todo

            }
        }
        return orderDTO;
    }

    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        if (OrderStatus.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            orderDTO.setOrderStatus(OrderStatus.FINISHED.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            OrderMaster save = orderMasterRepo.save(orderMaster);
            if (save == null) {
                throw new SellException(ResultEnum.UPDATE_ERROR);
            }
        }
        return orderDTO;

    }

    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        if (OrderStatus.NEW.getCode().equals(orderDTO.getOrderStatus())
                && PayStatus.WAIT.getCode().equals(orderDTO.getPayStatus())) {
            orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            OrderMaster save = orderMasterRepo.save(orderMaster);
            if (save == null) {
                throw new SellException(ResultEnum.UPDATE_ERROR);
            }
        }
        return orderDTO;

    }
}