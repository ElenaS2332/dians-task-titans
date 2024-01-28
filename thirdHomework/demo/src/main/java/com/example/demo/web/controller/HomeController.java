package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.service.HomeService;
import com.example.demo.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final AuthService authService;


    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    //za Hello Name
    public String getHomePage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");

        // Check if the user is logged in
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        model.addAttribute("bodyContent", "home");
        return "home";
    }
}
