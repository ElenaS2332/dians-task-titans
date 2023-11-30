package com.example.demo.web.servlet;

import com.example.demo.service.WineService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "Wine", urlPatterns = "/wineServlet")
public class WineServlet extends HttpServlet {

    private final WineService wineService;

    public WineServlet(WineService wineService) {
        this.wineService = wineService;
    }
}
