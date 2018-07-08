package com.originaldreams.usermanagercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UsermanagercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagercenterApplication.class, args);
    }
}
