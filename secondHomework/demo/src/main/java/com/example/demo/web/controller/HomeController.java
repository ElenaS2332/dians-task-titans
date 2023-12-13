package com.example.demo.web.controller;

import com.example.demo.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }
    @RequestMapping("/home")
    public String getHomePage(){
        return "home";
    }
}
