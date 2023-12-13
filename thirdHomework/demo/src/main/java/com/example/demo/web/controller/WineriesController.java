package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.model.Wineries;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wineries")
public class WineriesController {

    private List<Wineries> wineries;

    @GetMapping
    public String getWineries(HttpServletRequest request, Model model) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            // Pass the username to the template
            model.addAttribute("username", user.getUsername());
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/wineries.json")) {
            wineries = objectMapper.readValue(inputStream, new TypeReference<List<Wineries>>() {});
            model.addAttribute("wineries", wineries);

            // Print the list of wineries for verification
            wineries.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        model.addAttribute("bodyContent", "home");
        return "wineries";
    }

    @GetMapping("/wineries")
    public String getFilteredWineries(@RequestParam(required = false) String location, Model model) {
        // Perform filtering based on latitude and longitude
        List<Wineries> filteredWineries = filterWineriesByLocation(location);

        // Print filtered wineries for verification
        System.out.println("Filtered Wineries:");
        filteredWineries.forEach(System.out::println);

        // Add the filtered wineries to the model
        model.addAttribute("filteredWineries", filteredWineries);

        // Return the template
        return "wineries";
    }

    private List<Wineries> filterWineriesByLocation(String location) {
        // Implement your filtering logic here based on the provided location
        if (location == null || location.trim().isEmpty()) {
            // If location is empty or null, return all wineries
            return wineries;
        }

        // Otherwise, filter wineries by location
        return wineries.stream()
                .filter(winery -> location.equalsIgnoreCase(winery.getLocation()))
                .collect(Collectors.toList());
    }

}
