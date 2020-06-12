package com.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

    public String welcome() {

        return "Welcome to home page!";
    }
}
