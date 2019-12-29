package com.luo.house.biz.repo;

import com.luo.house.common.model.OrderMaster;
import com.luo.house.common.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer status);
}
