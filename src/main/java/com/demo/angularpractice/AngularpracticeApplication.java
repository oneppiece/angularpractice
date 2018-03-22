package com.demo.angularpractice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.angularpractice.repository")
public class AngularpracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngularpracticeApplication.class, args);
    }
}
