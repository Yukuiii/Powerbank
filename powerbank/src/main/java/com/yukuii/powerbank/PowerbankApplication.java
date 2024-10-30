package com.yukuii.powerbank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yukuii.powerbank.mapper")
public class PowerbankApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerbankApplication.class, args);
    }

}
