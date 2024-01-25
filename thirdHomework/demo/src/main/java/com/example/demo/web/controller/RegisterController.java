package com.example.demo.web.controller;

import com.example.demo.logger.WineryLogger;
import com.example.demo.model.*;
import com.example.demo.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")

public class RegisterController {
    private final AuthService authService;
    private final WineryLogger wineryLogger = WineryLogger.getInstance();

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("bodyContent", "register");
        return "register";
    }

    @PostMapping
    public String register(HttpServletRequest request, Model model, String username) {
        User user = null;
        Boolean userExist=authService.userExist(username);
        model.addAttribute("onRegister", "Hey");
        if (userExist) {
            String errorMessage = "User already exists. Please choose another username";
            model.addAttribute("errorMessage", errorMessage);
            wineryLogger.logRegistrationError(username, errorMessage);
            return "register";
        }
        String userUsername = request.getParameter("username");
        String userPassword = request.getParameter("password");
        String userRepeatedPassword = request.getParameter("repeatPassword");
        String userName = request.getParameter("name");
        String userSurname = request.getParameter("surname");

        if(userUsername.length() < 5)
        {
            String errorMessage = "Username should be at least 5 characters.";
            model.addAttribute("errorMessage", errorMessage);
            wineryLogger.logRegistrationError(username, errorMessage);
            return "register";
        }

        if (!userPassword.equals(userRepeatedPassword)){
            String errorMessage = "Passwords do not match. Please re-enter password.";
            model.addAttribute("errorMessage", errorMessage);
            wineryLogger.logRegistrationError(username, errorMessage);
            return "register";
        }

        if(userPassword.length() < 8){
            String errorMessage = "Password should be at least 8 characters.";
            model.addAttribute("errorMessage", errorMessage);
            wineryLogger.logRegistrationError(username, errorMessage);
            return "register";
        }

        user = authService.register(userUsername, userPassword, userRepeatedPassword, userName, userSurname);

        request.getSession().setAttribute("user", user);
        return "redirect:/login";
    }
}


