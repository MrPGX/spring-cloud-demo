package com.pgx.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "stock-service",path = "/stock/") // name = "应用服务名称，path = controller 中的RequestMapping 的请求路径"
public interface StockFeignService {

    // 声明需要调用的resful接口
    @RequestMapping("reduct")
    public String reduct();
}
