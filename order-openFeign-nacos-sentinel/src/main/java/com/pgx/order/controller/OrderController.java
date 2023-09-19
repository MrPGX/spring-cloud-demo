package com.pgx.order.controller;

import com.pgx.order.feign.ProductFeignService;
import com.pgx.order.feign.StockFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order/*")
public class OrderController {
   @Resource
    StockFeignService stockFeignService;
   @Resource
   ProductFeignService productFeignService;

    @RequestMapping("add")
    public String add(){
        System.out.println("下单成功！");
        String stock = stockFeignService.reduct();

        String product = productFeignService.getProduct("123");

        return "hello world " + stock + product ;
    }

}
