package com.pgx.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",path = "/products"/*,configuration = OpenFeignConfig.class*/)
public interface ProductFeignService {

    @GetMapping(value = "/{id}")
    String getProduct(@PathVariable(value = "id") String id);
}
