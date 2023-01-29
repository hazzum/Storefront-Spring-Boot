package com.hazzum.storefront.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ParentRestController {

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello, bitches! Time on server is "+LocalDateTime.now();

    }
}
