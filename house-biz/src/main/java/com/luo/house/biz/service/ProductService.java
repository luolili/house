package com.luo.house.biz.service;

import com.luo.house.biz.dto.CartDTO;
import com.luo.house.biz.exception.SellException;
import com.luo.house.biz.repo.ProductInfoRepo;
import com.luo.house.common.constants.ProductStatus;
import com.luo.house.common.constants.ResultEnum;
import com.luo.house.common.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductInfoRepo productInfoRepo;

    public ProductInfo getOne(String id) {
        return productInfoRepo.findOne(id);

    }

    //分页：new PageRequest(0,2)
    public Page<ProductInfo> getAll(Pageable pageable) {
        return productInfoRepo.findAll(pageable);

    }

    public List<ProductInfo> getUpAll() {
        return productInfoRepo.findByProductStatus(ProductStatus.UP.getCode());

    }

    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepo.save(productInfo);

    }

    public void addStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            String productId = cartDTO.getProductId();
            ProductInfo productInfo = productInfoRepo.findOne(productId);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int res = productInfo.getProductStock() + cartDTO.getProductQantity();
            if (res < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(res);
            productInfoRepo.save(productInfo);
        }
    }

    @Transactional
    public void decrStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            String productId = cartDTO.getProductId();
            ProductInfo productInfo = productInfoRepo.findOne(productId);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int res = productInfo.getProductStock() - cartDTO.getProductQantity();
            if (res < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(res);
            productInfoRepo.save(productInfo);
        }

    }
}