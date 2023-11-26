package com.example.order.service.impl;

import com.example.feign.store.PoductFeign;
import com.example.order.bean.OrderBean;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    PoductFeign productFeign;

    @Override
    @GlobalTransactional
    public String createOrder(OrderBean orderBean) {
        orderMapper.insert(orderBean);
        String aa = productFeign.reduceProduct("111");
        int aabb = 10/0;
        return aa;
    }
}
