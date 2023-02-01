package com.hazzum.storefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class Storefront {
    public static void main(String[] args) {
        SpringApplication.run(Storefront.class);
    }
}
