package com.luo.house.biz.repo;

import com.luo.house.common.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer status);
}
