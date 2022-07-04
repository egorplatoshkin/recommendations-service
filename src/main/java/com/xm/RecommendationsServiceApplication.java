package com.xm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RecommendationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationsServiceApplication.class, args);
    }

}
