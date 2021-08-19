package com.codewith.codewith.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@Controller
public class controller implements ErrorController {
    @GetMapping("/error")
    public String redirectRoot(){
        return "index.html";
    }

    public String getErrorPath(){
        return "/error";
    }
}