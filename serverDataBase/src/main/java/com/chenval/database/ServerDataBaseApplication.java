package com.chenval.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenval
 * @date 2020/6/18 18:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.chenval"})
public class ServerDataBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerDataBaseApplication.class, args);
    }
}
