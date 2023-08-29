package com.pgx.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{


    @Override
    @SentinelResource(value = "getUser")
    public String getUser() {
        return "查询用户";
    }
}
