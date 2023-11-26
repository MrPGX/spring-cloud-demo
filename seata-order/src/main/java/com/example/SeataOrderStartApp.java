package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.**.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderStartApp
{
    public static void main( String[] args )
    {
        SpringApplication.run( SeataOrderStartApp.class, args );
    }
}
