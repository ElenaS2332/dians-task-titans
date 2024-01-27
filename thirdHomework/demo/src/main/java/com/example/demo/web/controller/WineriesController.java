package com.example.demo.web.controller;

import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.model.Wine;
import com.example.demo.model.Wineries;
import com.example.demo.repository.WineriesRepository;
import com.example.demo.service.ReviewService;
import com.example.demo.service.WineriesService;
import com.example.demo.service.impl.WineriesServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wineries")
public class WineriesController {

    private List<Wineries> wineries;

    private final WineriesService wineriesService;

    public WineriesController(WineriesService wineriesService) {
        this.wineriesService = wineriesService;
    }

    @GetMapping
    public String getWineries(HttpServletRequest request, Model model) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/wineries.json")) {
            wineries = objectMapper.readValue(inputStream, new TypeReference<List<Wineries>>() {});
            model.addAttribute("wineries", wineries);

            List<String> locationOptions = wineries.stream()
                    .map(Wineries::getLocation)
                    .distinct()
                    .collect(Collectors.toList());

            model.addAttribute("locationOptions", locationOptions);
            model.addAttribute("selectedLocation", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("isFilterApplied", false);

        model.addAttribute("bodyContent", "home");
        return "wineries";
    }

    @GetMapping("/filter")
    public String getFilteredWineries(@RequestParam(required = false) String location, Model model) {
        List<Wineries> filteredWineries = filterWineriesByLocation(location);

        List<String> locationOptions = wineries.stream()
                .map(Wineries::getLocation)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("locationOptions", locationOptions);
        // Set the selectedLocation attribute based on the request parameter
        model.addAttribute("selectedLocation", location);
        model.addAttribute("wineries", wineries);

        // Initialize the filteredWineries attribute with the filtered list
        model.addAttribute("filteredWineries", filteredWineries);
        model.addAttribute("isFilterApplied", true);

        return "wineries";
    }


    private List<Wineries> filterWineriesByLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            return wineries;
        }

        return wineries.stream()
                .filter(winery -> location.equalsIgnoreCase(winery.getLocation()))
                .collect(Collectors.toList());
    }

}
