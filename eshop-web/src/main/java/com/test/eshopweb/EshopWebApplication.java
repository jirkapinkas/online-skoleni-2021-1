package com.test.eshopweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
// @SpringBootApplication v sobe schovava:
// @Configuration
// @ComponentScan
// @EnableAutoConfiguration
public class EshopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopWebApplication.class, args);
    }

}
