package com.clarit.hs.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClaritHSApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClaritHSApplication.class, args);
    }
}
