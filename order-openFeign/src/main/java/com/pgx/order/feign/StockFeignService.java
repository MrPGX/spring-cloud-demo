package com.pgx.order.feign;

import com.pgx.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// name = “服务名称”， path = "接口所在的Controller类指定的@RequestMapping"
@FeignClient(name="stock-service",path="stock"/*,configuration = OpenFeignConfig.class*/)
public interface StockFeignService {

    @RequestMapping("reduct")
     String reduct();
}
