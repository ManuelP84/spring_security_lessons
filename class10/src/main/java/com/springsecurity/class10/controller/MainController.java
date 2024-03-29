package com.springsecurity.class10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
 // @CrossOrigin("*") // All origins
    public String test(){
        System.out.println(":(");
        return "TEST!";
    }

}
