package com.example.feign.store;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "seata-store",path = "/store/products")
public interface PoductFeign {

    @PostMapping("/reduceProduct.html")
     String reduceProduct(String productId);
}
