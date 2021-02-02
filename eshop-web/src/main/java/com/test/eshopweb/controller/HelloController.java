package com.test.eshopweb.controller;

import com.test.eshopweb.pojo.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public Message message() {
        return new Message("Spring Boot Works! :-) ^_^");
    }

}
