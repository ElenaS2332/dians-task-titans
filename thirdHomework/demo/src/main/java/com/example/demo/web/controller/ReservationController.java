package com.example.demo.web.controller;

import com.example.demo.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @GetMapping
    //za Hello Name
    public String getReservationPage(HttpServletRequest request, Model model) {
        // Retrieve the user from the session
        User user = (User) request.getSession().getAttribute("user");

        // Check if the user is logged in
        if (user != null) {
            // Pass the username to the template
            model.addAttribute("username", user.getUsername());
        }

        model.addAttribute("bodyContent", "home");
        return "reservation";
    }
}
