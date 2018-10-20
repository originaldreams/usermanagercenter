package com.original.dreams.passport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan(value = {"com.originaldreams.passport.model.mapper"})
@EnableConfigurationProperties
@SpringBootApplication
public class PassportApplication {

    public static void main(String[] args) {

        SpringApplication.run(PassportApplication.class, args);
    }
}
