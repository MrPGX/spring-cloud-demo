package com.example.order.comtroller;

import com.example.order.bean.OrderBean;
import com.example.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RequestMapping("/order")
@RestController
public class Ordercontroller {
    @Resource
    OrderService orderService;


    @PostMapping(value = "/createOrder")
    public String createOrder(@RequestBody OrderBean bean){
/*        OrderBean bean = new OrderBean();
        bean.setOrderId("222");
        bean.setOrderName("test_222");*/
        String message = orderService.createOrder(bean);
        return message;
    }
}
