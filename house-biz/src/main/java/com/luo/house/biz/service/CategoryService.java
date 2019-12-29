package com.luo.house.biz.service;

import com.luo.house.biz.repo.ProductCategoryRepo;
import com.luo.house.common.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategory getOne(Integer id) {
        return productCategoryRepo.findOne(id);

    }

    public List<ProductCategory> getAll() {
        return productCategoryRepo.findAll();

    }
    
    public List<ProductCategory> getByCategoryTypeList(List<Integer> list) {
        return productCategoryRepo.findByCategoryTypeIn(list);

    }

    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);

    }
}