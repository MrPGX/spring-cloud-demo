package com.pgx.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/*")
public class StockController {

    @Value("${server.port}")
    String port;

    @RequestMapping("reduct")
    public String reduct(){
        System.out.println("扣减库存" + port);
        return "-----------------------扣减库存" + port + "-----------------------";
    }

    @RequestMapping("reduct2")
    public String reduct2(){
        int a = 10/0;
        System.out.println("扣减库存" + port);
        return "-----------------------扣减库存" + port + "-----------------------";
    }
}
