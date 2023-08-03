package com.pgx.nacosConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        String name = application.getEnvironment().getProperty("user.name");
        while (true){
            System.out.println("输出的nacos配置服务 : " + name);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
