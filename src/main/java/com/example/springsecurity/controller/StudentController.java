package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Student area!!!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Student";
    }
}
