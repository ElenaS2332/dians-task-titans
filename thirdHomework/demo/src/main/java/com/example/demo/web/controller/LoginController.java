package com.example.demo.web.controller;

import com.example.demo.model.InvalidArgumentsException;
import com.example.demo.model.InvalidUserExcepion;
import org.springframework.ui.Model;
import com.example.demo.model.User;
import com.example.demo.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/login")  // Add a common mapping for the entire controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping  // Specify the path for the GET method
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "login";
    }

    @PostMapping  // Specify the path for the POST method
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
            System.out.println("Try in LoginController. Session is: " + request.getSession());
        } catch (InvalidUserExcepion | InvalidArgumentsException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            System.out.println("Caught exception in LoginController. Session is: " + request.getSession());
            return "login";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}
