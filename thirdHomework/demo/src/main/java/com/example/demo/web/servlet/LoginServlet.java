//package com.example.demo.web.servlet;
//
//import com.example.demo.model.InvalidArgumentsException;
//import com.example.demo.model.InvalidUserExcepion;
//import com.example.demo.model.User;
//import com.example.demo.service.impl.AuthService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@RequestMapping("/login")
//@WebServlet
//public class LoginServlet extends HttpServlet {
//
//    private final AuthService authService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    @Autowired
//    public LoginServlet(AuthService authService, SpringTemplateEngine springTemplateEngine) {
//        this.authService = authService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @GetMapping
//    public String getLoginPage() {
//        return "login";
//    }
//
//    @PostMapping
//    public String login(HttpServletRequest request, Model model) {
//        User user = null;
//        try {
//            user = this.authService.login(
//                    request.getParameter("username"),
//                    request.getParameter("password"));
//            request.getSession().setAttribute("user", user);
////            return "redirect:/home";
//        } catch (InvalidUserExcepion exception) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
//
//        return "redirect:/home";
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//        springTemplateEngine.process(
//                "login.html",
//                context,
//                resp.getWriter()
//        );
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req,
//                          HttpServletResponse resp) throws ServletException, IOException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        User user = null;
//        try {
//            user = authService.login(username, password);
//        } catch (InvalidUserExcepion | InvalidArgumentsException ex) {
//            context.setVariable("hasError", true);
//            context.setVariable("error", ex.getMessage());
//            springTemplateEngine.process("login.html", context, resp.getWriter());
//        }
//        if (user != null) {
//            req.getSession().setAttribute("user", user);
//            resp.sendRedirect("/home");
//        }
//    }
//
//
//}
