package com.example.product.controller;

import com.example.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/store/products")
public class ProductStoreController {

    @Resource
    ProductService productService;

    @PostMapping("/reduceProduct.html")
    public String reduceProduct(String productId){
        productService.reduceProductById("111");

        return "成功扣减 " + productId + "商品";
    }
}
