package com.example.demo.web.servlet;

import com.example.demo.service.HomeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "Home", urlPatterns = "/homeServlet")
public class HomeServlet extends HttpServlet {

    private final HomeService homeService;

    public HomeServlet(HomeService homeService) {
        this.homeService = homeService;
    }
}
