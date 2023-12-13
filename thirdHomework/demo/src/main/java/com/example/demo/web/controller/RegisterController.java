package com.example.demo.web.controller;

import com.example.demo.model.InvalidArgumentsException;
import com.example.demo.model.InvalidUserExcepion;
import com.example.demo.model.PasswordsDoNotMatchException;
import com.example.demo.model.User;
import com.example.demo.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register"; //  registration page.
    }

    @PostMapping  // Specify the path for the POST method
    public String register(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.register(request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("repeatPassword"),
                request.getParameter("name"),
                request.getParameter("surname"));
            System.out.println("Try in RegisterController. Session is: " + request.getSession());

        } catch (InvalidUserExcepion | InvalidArgumentsException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            System.out.println("Caught exception in RegisterController. Session is: " + request.getSession());
            return "home";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}


