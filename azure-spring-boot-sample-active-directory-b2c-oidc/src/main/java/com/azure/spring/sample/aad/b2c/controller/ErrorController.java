package com.azure.spring.sample.aad.b2c.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/errorPage")
    public String errorPage(String errorMsg){
        //todo
        return "errorPage";
    }
}
