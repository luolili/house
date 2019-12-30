package com.luo.house.web;

import com.luo.house.biz.service.CategoryService;
import com.luo.house.biz.service.ProductService;
import com.luo.house.common.model.ProductCategory;
import com.luo.house.common.model.ProductInfo;
import com.luo.house.common.result.ResultVO;
import com.luo.house.vo.ProductInfoVO;
import com.luo.house.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("buyer/product")
public class BuyerProductCtr {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public ResultVO getAll() {
        List<ProductInfo> productInfoList = productService.getUpAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> typeList = categoryService.getByCategoryTypeList(categoryTypeList);
        //组装
        List<ProductVO> productVOList = getProductVOS(productInfoList, typeList);
        return ResultVO.success(productVOList);
    }

    private List<ProductVO> getProductVOS(List<ProductInfo> productInfoList, List<ProductCategory> typeList) {
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : typeList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo info : productInfoList) {
                if (category.getCategoryType().equals(info.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info, productInfoVO);
                    productInfoVOList.add(productInfoVO);

                }
                productVO.setProductInfoVOList(productInfoVOList);
            }
            productVOList.add(productVO);
        }
        return productVOList;
    }
}
