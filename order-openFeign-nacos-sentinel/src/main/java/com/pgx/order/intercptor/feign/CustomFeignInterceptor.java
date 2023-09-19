package com.pgx.order.intercptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// openFeign拦截器
@Configuration
public class CustomFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info(requestTemplate.path());
    }
}
