package com.originaldreams.passport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan(value = {"com.originaldreams.usermanager.model.mapper"})
@EnableConfigurationProperties
@SpringBootApplication
public class PassportApplication {

    public static void main(String[] args) {

        SpringApplication.run(PassportApplication.class, args);
    }
}
