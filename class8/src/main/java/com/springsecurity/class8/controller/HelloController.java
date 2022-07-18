package com.springsecurity.class8.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Async
    public String hello(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        return "Hello " + auth.getName();
    }

}
