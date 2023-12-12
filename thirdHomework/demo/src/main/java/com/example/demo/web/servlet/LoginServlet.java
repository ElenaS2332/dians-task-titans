package com.example.demo.web.servlet;

import com.example.demo.model.InvalidUserExcepion;
import com.example.demo.model.User;
import com.example.demo.service.impl.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginServlet {

private final AuthService authService;

    public LoginServlet(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public  String getLoginPage(){return "login";}
@PostMapping
    public  String login(HttpServletRequest reguest, Model model) {
    User user = null;
    try {
        user = this.authService.login(reguest.getParameter("username"),
                reguest.getParameter("password"));
        reguest.getSession().setAttribute("user", user);
        return "redirect:/home";
//ako sakate home
    } catch (InvalidUserExcepion excepion) {
model.addAttribute("hasError",true);
model.addAttribute("error",excepion.getMessage());
    return  "login";

    }
}










}
