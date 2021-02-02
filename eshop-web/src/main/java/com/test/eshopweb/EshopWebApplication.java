package com.test.eshopweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
