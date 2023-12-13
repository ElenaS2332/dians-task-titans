package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.service.WineService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/wine")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    //za Hello Name
    public String getWinePage(HttpServletRequest request, Model model) {
        // Retrieve the user from the session
        User user = (User) request.getSession().getAttribute("user");

        // Check if the user is logged in
        if (user != null) {
            // Pass the username to the template
            model.addAttribute("username", user.getUsername());
        }

        model.addAttribute("bodyContent", "home");
        return "wine";
    }
}
