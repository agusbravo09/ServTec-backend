package com.example.ServTec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "the api is working fine... Greetings";
    }
}
