package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wineries")
public class WineriesController {

    @GetMapping
    public String getWineriesPage(){
        return "wineries";
    }
}
