package com.pgx.order.controller;

import com.alibaba.csp.sentinel.adapter.spring.webflux.exception.SentinelBlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pgx.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orderSentinel/*")
public class OrderController {
    @Resource
    OrderService orderService;

    @RequestMapping("add")
    public String add(){
        System.out.println("下单成功！");

        return "hello world ";
    }

    @RequestMapping("flow")
    @SentinelResource(blockHandler = "flowBlockHandler")
    public String flow(){
        System.out.println("flow");
        return "flow";
    }

    public String flowBlockHandler(BlockException e){
        System.out.println("flowBlockHandler");
        return "flowBlockHandler";

    }

    @GetMapping("test1")
    @ResponseBody
    public String test1(){
        return orderService.getUser();
    }

    @GetMapping("test2")
    @ResponseBody
    public String test2(){
        return orderService.getUser();
    }

}
