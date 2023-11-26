package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.example.**.mapper")
@EnableDiscoveryClient
public class SeataStoreApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SeataStoreApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
