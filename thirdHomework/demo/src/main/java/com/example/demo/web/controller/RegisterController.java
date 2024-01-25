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
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class RegisterController {
    private final AuthService authService;
    private final WineryLogger wineryLogger = WineryLogger.getInstance();

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; //  registration page.
    }

    @PostMapping ("/register") // Specify the path for the POST method
    public String register(HttpServletRequest request, Model model,String username) {
        User user = null;
        Boolean userExist=authService.userExist(username);
        if (userExist) {
            return "redirect:/register?error=Username already exists";
        }
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
            wineryLogger.logRegistrationError(request.getParameter("username"), exception.getMessage());
            return "redirect:/register";
        }catch (NonUniqueUsernameException e) {
            // Username is not unique, add error message to the model
            model.addAttribute("errorMessage", e.getMessage());
            // Redirect back to the registration page
            return "redirect:/register";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/login";
    }
}


