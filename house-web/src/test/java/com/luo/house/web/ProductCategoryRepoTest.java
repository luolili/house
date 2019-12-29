package com.luo.house.web;

import com.luo.house.biz.repo.ProductCategoryRepo;
import com.luo.house.common.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductCategoryRepo.class})
@Slf4j
public class ProductCategoryRepoTest {

    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Test
    public void testFind() {
        ProductCategory one = productCategoryRepo.findOne(1);
        log.info("it :[{}]", one);

    }
}