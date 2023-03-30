package com.pgx.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/*")
public class OrderController {
    @Qualifier("restTemplate")
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("add")
    public String add(){
        System.out.println("下单成功！");
        ResponseEntity<String> msg = restTemplate.getForEntity("http://localhost:8011/stock/reduct",String.class);

        return "hello world " + msg;
    }
}
