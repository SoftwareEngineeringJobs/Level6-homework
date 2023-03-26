package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class TestController {

    // 访问 http://localhost/返回 hello world
    @GetMapping
    public String helloWorld() {
        return "hello world";
    }

    // 访问 http://localhost/world  返回 world
    @GetMapping("/world")
    public String World() {
        return "world";
    }



}
