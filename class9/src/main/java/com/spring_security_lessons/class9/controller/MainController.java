package com.spring_security_lessons.class9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping
    public String main(){
        return "main.html";
    }

    @PostMapping("/change")
    public  String doSomething(){
        System.out.println(":(");
        return "main.html";
    }
}
